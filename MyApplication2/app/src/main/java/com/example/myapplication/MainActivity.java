package com.example.myapplication;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private View view;
    private float x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        view = findViewById(R.id.view);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_MOVE://移动时
                        x = motionEvent.getRawX();
                        y = motionEvent.getRawY();
                        view.setX(x);
                        view.setY(y);
                        break;
                    case MotionEvent.ACTION_DOWN://按下时

                        break;
                    case MotionEvent.ACTION_UP://按住抬起时

                        break;
                }
                return true;
            }
        });
    }
}