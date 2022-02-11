package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.adapter.MyFragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> videoPath = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏头部标题
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//设置状态栏透明
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //动态获取权限
        try {
            String[] permArr = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS
            };
            boolean needReq = false;
            for (int i = 0; i < permArr.length; i++) {
                if (ContextCompat.checkSelfPermission(this, permArr[i]) != PackageManager.PERMISSION_GRANTED) {
                    needReq = true;
                    break;
                }
            }
            if (needReq) {
                ActivityCompat.requestPermissions(this, permArr, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);

        initData();
        ViewPager2 viewpage2 = findViewById(R.id.page2);
         viewpage2.setOffscreenPageLimit(2);
        MyFragmentStateAdapter adapter = new MyFragmentStateAdapter(this, videoPath,this);
        viewpage2.setAdapter(adapter);
       // viewpage2.setCurrentItem(2);
        viewpage2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                adapter.createFragment(position).onStart();
            }
        });
    }

    /**
     * 初始化视频数据
     */
    private void initData() {
        //String baseurl="http://18021859626.qicp.vip";
        String baseurl = "http://42.192.226.132";
        for (int i = 1; i < 11; i++) {
            videoPath.add(baseurl + "/video/" + i + ".mp4");
        }
    }
}