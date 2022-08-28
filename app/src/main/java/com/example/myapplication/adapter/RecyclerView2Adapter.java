package com.example.myapplication.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.List;


public class RecyclerView2Adapter extends RecyclerView.Adapter<RecyclerView2Adapter.ViewHolder>{

    Context context;
    List<String> images;
    List<String> titles;
    List<String> content;
    List<String> comment;
    List<String> time;

    public RecyclerView2Adapter(List<String> images, List<String> titles, List<String> content, List<String> comment, List<String> time) {
        this.images = images;
        this.titles = titles;
        this.content = content;
        this.comment = comment;
        this.time = time;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView10.setText(titles.get(position));
        holder.textView11.setText(content.get(position));
        holder.textView12.setText("评论总数：" + comment.get(position));
        holder.textView13.setText("发布时间：" + time.get(position));
        Glide.with(context).load(images.get(position)).into(holder.imageView3);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView3;
        TextView textView10;
        TextView textView11;
        TextView textView12;
        TextView textView13;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView3 = itemView.findViewById(R.id.imageView3);
            textView10 = itemView.findViewById(R.id.textView10);
            textView11 = itemView.findViewById(R.id.textView11);
            textView12 = itemView.findViewById(R.id.textView12);
            textView13 = itemView.findViewById(R.id.textView13);
        }
    }
}
