package com.example.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.List;

public class HotTopicAdapter extends BaseAdapter {

    Context context;
    List<String> titles;
    List<String> images;

    public HotTopicAdapter(Context context, List<String> titles, List<String> images) {
        this.context = context;
        this.titles = titles;
        this.images = images;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int position) {
        return titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.hot_item, null);
        ImageView imageView = view.findViewById(R.id.imageView2);
        TextView textView = view.findViewById(R.id.textView8);
        Glide.with(context).load(images.get(position)).into(imageView);
        textView.setText(titles.get(position));
        return view;
    }
}
