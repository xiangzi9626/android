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
    private List<Integer> images = new ArrayList<>();
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
        images.add(R.drawable.banner_1);
        images.add(R.drawable.banner_2);
        images.add(R.drawable.banner_3);
        images.add(R.drawable.banner_4);
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