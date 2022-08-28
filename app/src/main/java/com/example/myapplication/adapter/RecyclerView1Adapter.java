package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class RecyclerView1Adapter extends RecyclerView.Adapter<RecyclerView1Adapter.ViewHolder>{

    private List<String> newsType;

    public RecyclerView1Adapter(List<String> newsType) {
        this.newsType = newsType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_type_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(newsType.get(position));
    }


    @Override
    public int getItemCount() {
        return newsType.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView9);
        }
    }

}
