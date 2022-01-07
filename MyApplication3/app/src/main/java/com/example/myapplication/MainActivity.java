package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private int lastPosition;
    private List<String> images = new ArrayList<>();
    private LinearLayout indicatorContainer;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化组件
        viewPager2 = findViewById(R.id.viewpager2);
        indicatorContainer = findViewById(R.id.container_indicator);
        initImages();
        //初始化指示点
        initIndicatorDots();
        //添加适配器
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(images);
        viewPager2.setAdapter(viewPagerAdapter);
        //设置轮播图初始位置在500 以保证手动可以前翻
        lastPosition = 500;
        //注册轮播图的滚动事件监听
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //轮播时改变指示器
                int current = position % 4;
                int last = lastPosition % 4;
                indicatorContainer.getChildAt(current).setBackgroundResource(R.drawable.indicator2_shape);
                indicatorContainer.getChildAt(last).setBackgroundResource(R.drawable.indicator_shape);
                lastPosition = position;
            }
        });
    }

    private void initImages() {
        images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fku.90sjimg.com%2Fback_pic%2F04%2F05%2F98%2F39580ed7fd33bab.jpg&refer=http%3A%2F%2Fku.90sjimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644128523&t=db473f1f035d795234f27b82db6e1b9e");
        images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fku.90sjimg.com%2Fback_pic%2F03%2F76%2F50%2F7457becb1966fba.jpg%21%2Fwatermark%2Ftext%2FOTDorr7orqE%3D%2Ffont%2Fsimkai%2Falign%2Fsoutheast%2Fopacity%2F20%2Fsize%2F50&refer=http%3A%2F%2Fku.90sjimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644128523&t=90544f54a639a6d57bf8e27e962cdc21");
        images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F00%2F52%2F00%2Fc4675989e4b6bbfb676305e408c60579.jpg&refer=http%3A%2F%2Fpic.90sjimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644128523&t=04242bc8f0ac22b153bf0f25a93f1900");
        images.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fku.90sjimg.com%2Fback_pic%2F04%2F11%2F67%2F12581ab070b56fa.jpg&refer=http%3A%2F%2Fku.90sjimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644128523&t=d779fbe862058ef42ec044fc4cad3484");
    }

    /**
     * 初始化指示点
     */
    private void initIndicatorDots() {
        for (int i = 0; i < images.size(); i++) {
            ImageView imageView = new ImageView(this);
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.indicator2_shape);
            } else {
                imageView.setBackgroundResource(R.drawable.indicator_shape);
            }
            //为指示点添加间距
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMarginEnd(4);
            imageView.setLayoutParams(layoutParams);
            //将指示点添加进容器
            indicatorContainer.addView(imageView);
        }
    }

    /**
     * 当应用唤醒时让轮播图轮播
     */
    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //获得轮播图当前的位置
            int currentPosition = viewPager2.getCurrentItem();
            currentPosition++;
            viewPager2.setCurrentItem(currentPosition, true);
            handler.postDelayed(runnable, 3000);
        }
    };
}