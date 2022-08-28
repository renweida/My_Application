package com.example.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.List;

public class ServiceAdapter extends BaseAdapter {

    private List<String> titles;
    private Context context;
    private List<String> images;

    public ServiceAdapter(Context context, List<String> titles, List<String> images) {
        this.titles = titles;
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return titles.size() + 1;
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
        View view = View.inflate(context, R.layout.service_item, null);
        TextView textView = view.findViewById(R.id.textView3);
        ImageView imageView = view.findViewById(R.id.imageView);
        if (position == 9) {
            textView.setText("更多服务");
            return view;
        }
        textView.setText(titles.get(position));
        Glide.with(context).load(images.get(position)).into(imageView);
        return view;
    }
}
