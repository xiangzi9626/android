package com.example.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fragment.VideoFragment;

import java.util.List;

public class MyFragmentStateAdapter extends FragmentStateAdapter {
    private List<String> videoPath;
    private Context context;

    public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity, List<String> videoPath,Context context) {
        super(fragmentActivity);
        this.context = context;
        this.videoPath = videoPath;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new VideoFragment(context,videoPath.get(position));
     }
    @Override
    public int getItemCount() {
         return videoPath.size();
    }
}
