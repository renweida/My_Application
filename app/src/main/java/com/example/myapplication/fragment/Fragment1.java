package com.example.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.APIService;
import com.example.myapplication.HouseActivity;
import com.example.myapplication.R;
import com.example.myapplication.RetrofitManager;
import com.example.myapplication.adapter.HotTopicAdapter;
import com.example.myapplication.adapter.RecyclerView1Adapter;
import com.example.myapplication.adapter.RecyclerView2Adapter;
import com.example.myapplication.adapter.ServiceAdapter;
import com.example.myapplication.bean.HotTopic;
import com.example.myapplication.bean.NewsList;
import com.example.myapplication.bean.NewsType;
import com.example.myapplication.bean.Rotation;
import com.example.myapplication.bean.Service;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment1 extends Fragment {

    private GridView gridView;
    private GridView gridView2;
    private Banner banner;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = view.findViewById(R.id.gridView);
        gridView2 = view.findViewById(R.id.gridView2);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        banner = view.findViewById(R.id.banner);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 7:
                        startActivity(new Intent(getActivity(), HouseActivity.class));
                        break;
                }
            }
        });

        APIService service = RetrofitManager.getRetrofit().create(APIService.class);
        Call<Rotation> call = service.getBannerResource();
        call.enqueue(new Callback<Rotation>() {
            @Override
            public void onResponse(Call<Rotation> call, Response<Rotation> response) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < response.body().getTotal(); i++) {
                    list.add("http://218.7.112.123:10001" + response.body().getRows().get(i).getAdvImg());
                }
                Log.d("sadfasdfasdfsa", String.valueOf(list));
                banner.setImages(list).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(path).into(imageView);
                    }
                }).start();
            }

            @Override
            public void onFailure(Call<Rotation> call, Throwable t) {

            }
        });
        Call<Service> serviceCall = service.getAllService();
        serviceCall.enqueue(new Callback<Service>() {
            @Override
            public void onResponse(Call<Service> call, Response<Service> response) {
                List<String> titles = new ArrayList<>();
                List<String> images = new ArrayList<>();
                for (int i = 0; i < 9; i++) {
                    titles.add(response.body().getRows().get(i).getServiceName());
                    images.add("http://218.7.112.123:10001" + response.body().getRows().get(i).getImgUrl());
                }
                gridView.setAdapter(new ServiceAdapter(getContext(), titles, images));
            }

            @Override
            public void onFailure(Call<Service> call, Throwable t) {

            }
        });
        Call<HotTopic> hotTopicCall = service.getHotTopic();
        hotTopicCall.enqueue(new Callback<HotTopic>() {
            @Override
            public void onResponse(Call<HotTopic> call, Response<HotTopic> response) {
                List<String> titles = new ArrayList<>();
                List<String> images = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    titles.add(response.body().getRows().get(i).getTitle());
                    images.add("http://218.7.112.123:10001" + response.body().getRows().get(i).getCover());
                }
                gridView2.setAdapter(new HotTopicAdapter(getContext(), titles, images));
            }

            @Override
            public void onFailure(Call<HotTopic> call, Throwable t) {

            }
        });
        Call<NewsType> newsTypeCall = service.getNewsType();
        newsTypeCall.enqueue(new Callback<NewsType>() {
            @Override
            public void onResponse(Call<NewsType> call, Response<NewsType> response) {
                List<String> types = new ArrayList<>();
                for (int i = 0; i < response.body().getData().size(); i++) {
                    types.add(response.body().getData().get(i).getName());
                }
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(new RecyclerView1Adapter(types));
            }

            @Override
            public void onFailure(Call<NewsType> call, Throwable t) {

            }
        });
        Call<NewsList> newsListCall = service.getNewsList();
        newsListCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                List<String> image = new ArrayList<>();
                List<String> title = new ArrayList<>();
                List<String> content = new ArrayList<>();
                List<String> comment = new ArrayList<>();
                List<String> time = new ArrayList<>();
                for (int i = 0; i < response.body().getTotal(); i++) {
                    image.add("http://218.7.112.123:10001" + response.body().getRows().get(i).getCover());
                    title.add(response.body().getRows().get(i).getTitle());
                    content.add(response.body().getRows().get(i).getContent());
                    comment.add(String.valueOf(response.body().getRows().get(i).getCommentNum()));
                    time.add(response.body().getRows().get(i).getPublishDate());
                }
                recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView2.setAdapter(new RecyclerView2Adapter(image, title, content, comment, time));
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_1, container, false);
    }
}
