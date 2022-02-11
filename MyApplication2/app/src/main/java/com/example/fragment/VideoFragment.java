package com.example.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

import java.util.HashMap;

public class VideoFragment extends Fragment {
    private String url;
    private VideoView vv;
    private Context context;
    private int currentPosition = 1;

    public VideoFragment(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item, null);
        vv = view.findViewById(R.id.videoview);
        Uri uri = Uri.parse(url);
        vv.setVideoURI(uri);
        vv.requestFocus();
        //vv.setVideoPath(url);
        vv.seekTo(1);
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                View process = view.findViewById(R.id.progressBar);
                View process2 = view.findViewById(R.id.progressBar2);
                process.setVisibility(View.GONE);
                process2.setVisibility(View.GONE);
            }
        });
        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                vv.start();
            }
        });
        vv.start();
        vv.pause();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        vv.seekTo(currentPosition);
        vv.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        vv.pause();
    }


    @Override
    public void onStop() {
        super.onStop();
        currentPosition = vv.getCurrentPosition();
        vv.pause();
    }

    /**
     * 获取视频第一帧作为背景图
     *
     * @param url
     * @return
     */
    public Bitmap getVideoBitmap(String url) {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(url, new HashMap<>());
            bitmap = mediaMetadataRetriever.getFrameAtTime();
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        } finally {
            mediaMetadataRetriever.release();
        }
        return bitmap;
    }
}
