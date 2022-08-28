package com.example.myapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://218.7.112.123:10001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
