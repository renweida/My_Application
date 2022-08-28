package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.bean.Login;
import com.example.myapplication.fragment.Fragment1;
import com.example.myapplication.fragment.Fragment2;
import com.example.myapplication.fragment.Fragment3;
import com.example.myapplication.fragment.Fragment4;
import com.example.myapplication.fragment.Fragment5;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup2;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private Fragment5 fragment5;

    public static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment1).commit();
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment1).commit();
                        break;
                    case R.id.rb2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment2).commit();
                        break;
                    case R.id.rb3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment3).commit();
                        break;
                    case R.id.rb4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment4).commit();
                        break;
                    case R.id.rb5:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment5).commit();
                        break;
                }
            }
        });
        Map<String, String> map = new HashMap<>();
        map.put("username", "23333");
        map.put("password", "654321");
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), new JSONObject(map).toString());
        APIService service = RetrofitManager.getRetrofit().create(APIService.class);
        Call<Login> call = service.getToken(body);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                token = response.body().getToken();
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

            }
        });
    }

    private void initView() {
        radioGroup2 = findViewById(R.id.radioGroup2);
    }
}