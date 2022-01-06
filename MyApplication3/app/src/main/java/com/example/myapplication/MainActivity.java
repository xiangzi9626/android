package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.adapter.NewsAdapter;
import com.example.entity.News;
import com.example.loadRefresh.RecyclerViewScrollListener;
import com.example.utils.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout swiperefreshlayout;
    private RecyclerView recyclerView;
    private List<News> newsList = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private NewsAdapter adapter;
    private int page = 0;
    private Boolean b = true;//是否有更多数据
    private Handler handle = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what) {
                case 0://初始化数据
                    //adapter渲染数据
                    adapter = new NewsAdapter((List<News>) message.obj);
                    recyclerView.setAdapter(adapter);
                    break;
                case 1:
                    adapter.notifyDataSetChanged();
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setFocusableInTouchMode(false);
        recyclerView.setFocusable(false);
        recyclerView.setHasFixedSize(true);
        swiperefreshlayout = findViewById(R.id.swiperefreshlayout);
        //改变下拉刷新时的颜色
        swiperefreshlayout.setColorSchemeResources(R.color.design_default_color_surface);
        //设置下拉进度的背景颜色
        swiperefreshlayout.setProgressBackgroundColorSchemeResource(R.color.black);
        //隐藏刷新进度
        swiperefreshlayout.setRefreshing(false);
        //关闭刷新进度条
        swiperefreshlayout.setEnabled(false);
        initData();
        recyclerView.addOnScrollListener(new RecyclerViewScrollListener() {
            @Override
            public void onScrollToBottom() {
                // 加载更多
                if (b) {
                    page++;
                    loadMore();
                    Toast.makeText(MainActivity.this, "加载更多", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://192.168.2.5/app/controller/Mainactivity.php";
                    String params = "page=" + 0;
                    //发送请求得到数据
                    String res = HttpUtil.post(url, params);
                    Gson gson = new Gson();
                    newsList = gson.fromJson(res, new TypeToken<List<News>>() {
                    }.getType());
                    Message msg = new Message();
                    msg.what = 0;
                    msg.obj = newsList;
                    handle.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 加载更多
     */
    public void loadMore() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!b) {
                    return;
                }
                try {
                    String url = "http://192.168.2.5/app/controller/Mainactivity.php";
                    String params = "page=" + page;
                    String res = HttpUtil.post(url, params);
                    Gson gson = new Gson();
                    List<News> tmpList = new ArrayList<>();
                    tmpList = gson.fromJson(res, new TypeToken<List<News>>() {
                    }.getType());
                    if (tmpList.size() == 0) {
                        b = false;
                        return;
                    }
                    newsList.addAll(tmpList);
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = newsList;
                    handle.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}