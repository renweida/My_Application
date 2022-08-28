package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;

import com.bumptech.glide.Glide;
import com.example.myapplication.bean.HouseDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HouseDetailActivity extends AppCompatActivity {

    private Button button5;
    private ImageView imageView5;
    private TextView textView17;
    private TextView textView21;
    private TextView textView22;
    private TextView textView23;
    private TextView textView24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detail);
        getSupportActionBar().setTitle("房源详情");
        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
        int id = getIntent().getIntExtra("id", 1);
        APIService service = RetrofitManager.getRetrofit().create(APIService.class);
        Call<HouseDetail> houseDetailCall = service.getHouseDetail(id);
        houseDetailCall.enqueue(new Callback<HouseDetail>() {
            @Override
            public void onResponse(Call<HouseDetail> call, Response<HouseDetail> response) {
                textView17.setText(response.body().getData().getSourceName());
                textView21.setText(response.body().getData().getDescription());
                textView22.setText(String.valueOf(response.body().getData().getAreaSize()));
                textView23.setText(response.body().getData().getPrice());
                textView24.setText(response.body().getData().getHouseType());
                Glide.with(HouseDetailActivity.this)
                        .load("http://218.7.112.123:10001" +
                                response.body().getData().getPic())
                        .into(imageView5);
            }

            @Override
            public void onFailure(Call<HouseDetail> call, Throwable t) {

            }
        });
    }

    private void initView() {
        imageView5 = findViewById(R.id.imageView5);
        textView17 = findViewById(R.id.textView17);
        textView21 = findViewById(R.id.textView21);
        textView22 = findViewById(R.id.textView22);
        textView23 = findViewById(R.id.textView23);
        textView24 = findViewById(R.id.textView24);
    }
}