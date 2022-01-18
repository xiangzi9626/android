package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;

import com.example.surfaceview.MySurfaceView;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private SurfaceView mSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MySurfaceView view=new MySurfaceView(this);
        setContentView(R.layout.activity_main);
        mMediaPlayer=new MediaPlayer();
        mSurfaceView=findViewById(R.id.surfaceView1);
    }
    public void play(View view){
        mMediaPlayer.reset();
        Uri uri=Uri.parse("http://18021859626.qicp.vip/video/1.mp4");
        try{
            mMediaPlayer.setDataSource(this,uri);
            mMediaPlayer.setDisplay(mSurfaceView.getHolder());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void stop(View view){
        if (mMediaPlayer.isPlaying()){
            mMediaPlayer.stop();
        }
    }
    public void pause(View view){
        if (mMediaPlayer.isPlaying()){
            mMediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
       if (mMediaPlayer!=null && mMediaPlayer.isPlaying()){
           mMediaPlayer.stop();
       }
       if (mMediaPlayer!=null){
           mMediaPlayer.release();
           mMediaPlayer=null;
       }
       super.onDestroy();
    }
}