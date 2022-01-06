package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.entity.News;
import com.example.myapplication.R;

import java.util.List;
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> mNewsList;
   static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        public ViewHolder(View view) {
            super(view);
            iv = view.findViewById(R.id.iv);
            tv = view.findViewById(R.id.tv);
        }

    }

    public NewsAdapter(List<News> newsList) {
        mNewsList = newsList;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News news = mNewsList.get(position);
       Context context=holder.itemView.getContext();
        Glide.with(context).load(news.getImg()).into(holder.iv);
        holder.tv.setText(news.getId()+"/"+news.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.itemView.getContext(), "点击的是" + holder.getAdapterPosition(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }
}