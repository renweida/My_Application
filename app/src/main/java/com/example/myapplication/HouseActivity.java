package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;

import com.example.myapplication.adapter.HouseListAdapter;
import com.example.myapplication.adapter.HouseTypeAdapter;
import com.example.myapplication.bean.HouseList;
import com.example.myapplication.bean.HouseListBean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HouseActivity extends AppCompatActivity {

    private EditText editText4;
    private GridView gridView5;
    private RecyclerView recyclerView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        getSupportActionBar().setTitle("找房子");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText4 = findViewById(R.id.editText4);
        gridView5 = findViewById(R.id.gridView5);
        recyclerView4 = findViewById(R.id.recyclerView4);
        APIService service = RetrofitManager.getRetrofit().create(APIService.class);
        List<String> list = new ArrayList<>();
        list.add("二手");
        list.add("租房");
        list.add("楼盘");
        list.add("中介");
        gridView5.setAdapter(new HouseTypeAdapter(this, list));
        gridView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Call<HouseList> call = service.getHouseList("二手");
                        sendCall(call);
                        break;
                    case 1:
                        Call<HouseList> call2 = service.getHouseList("租房");
                        sendCall(call2);
                        break;
                    case 2:
                        Call<HouseList> call3 = service.getHouseList("楼盘");
                        sendCall(call3);
                        break;
                    case 3:
                        Call<HouseList> call4 = service.getHouseList("中介");
                        sendCall(call4);
                        break;
                }
            }
        });

        Call<HouseList> houseListCall = service.getHouseList();
        sendCall(houseListCall);
    }

    private void sendCall(Call<HouseList> call) {
        call.enqueue(new Callback<HouseList>() {
            @Override
            public void onResponse(Call<HouseList> call, Response<HouseList> response) {
                List<HouseListBean> list = new ArrayList<>();
                for (int i = 0; i < response.body().getTotal(); i++) {
                    HouseListBean bean = new HouseListBean();
                    bean.setImage("http://218.7.112.123:10001" + response.body().getRows().get(i).getPic());
                    bean.setAddress(response.body().getRows().get(i).getAddress());
                    bean.setArea(response.body().getRows().get(i).getAreaSize() + "平方米");
                    bean.setName(response.body().getRows().get(i).getSourceName());
                    bean.setPrice(response.body().getRows().get(i).getPrice());
                    bean.setDescription(response.body().getRows().get(i).getDescription());
                    bean.setType(response.body().getRows().get(i).getHouseType());
                    bean.setId(response.body().getRows().get(i).getId());
                    list.add(bean);
                }
                recyclerView4.setLayoutManager(new LinearLayoutManager(HouseActivity.this));
                recyclerView4.setAdapter(new HouseListAdapter(HouseActivity.this, list));
            }

            @Override
            public void onFailure(Call<HouseList> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}