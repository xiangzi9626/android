package com.example.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btn1;
    private ProgressDialog progressDialog;
    private static final int MSG_PROGRESS = 0x0001;
    private final static int MSG_FINISH = 0X0002;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what) {
                case MSG_PROGRESS:
                    progressDialog.setProgress(message.arg1);
                    break;
                case MSG_FINISH:
                    progressDialog.dismiss();
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        progressDialog = new ProgressDialog(this);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setIcon(R.mipmap.ic_launcher);
                progressDialog.setMessage("这是一个进度条");
                progressDialog.setTitle("进度条");
                //设置水平进度条
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i <= 100; i++) {
                            Message msg = handler.obtainMessage();
                            msg.arg1 = i;
                            msg.what = MSG_PROGRESS;
                            handler.sendMessage(msg);
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        handler.sendEmptyMessage(MSG_FINISH);
                    }
                }).start();
            }
        });
    }
}