package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.HouseDetailActivity;
import com.example.myapplication.R;
import com.example.myapplication.bean.HouseListBean;

import java.util.List;

public class HouseListAdapter extends RecyclerView.Adapter<HouseListAdapter.ViewHolder>{

    Context context;
    List<HouseListBean> list;

    public HouseListAdapter(Context context, List<HouseListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_house_list, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(context, HouseDetailActivity.class);
                intent.putExtra("id", list.get(position).getId());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImage()).into(holder.imageView4);
        holder.textView16.setText(list.get(position).getName());
        holder.textView18.setText(list.get(position).getAddress());
        holder.textView19.setText(list.get(position).getArea());
        holder.textView15.setText(list.get(position).getPrice());
        holder.textView14.setText(list.get(position).getDescription());
        holder.textView20.setText(list.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        private ImageView imageView4;
        private TextView textView14;
        private TextView textView15;
        private TextView textView16;
        private TextView textView18;
        private TextView textView19;
        private TextView textView20;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            imageView4 = itemView.findViewById(R.id.imageView4);
            textView14 = itemView.findViewById(R.id.textView14);
            textView15 = itemView.findViewById(R.id.textView15);
            textView16 = itemView.findViewById(R.id.textView16);
            textView18 = itemView.findViewById(R.id.textView18);
            textView19 = itemView.findViewById(R.id.textView19);
            textView20 = itemView.findViewById(R.id.textView20);
        }
    }
}
