package com.example.myapplication;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.utils.BitmapUtil;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;
    private String imgPath = "https://p3.img.cctvpic.com/photoAlbum/page/performance/img/2022/1/1/1641046755113_645.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv =(ImageView) findViewById(R.id.iv);
        Glide.with(this).load(imgPath).into(iv);
    }
}
