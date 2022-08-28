package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.bean.ModifyPassword;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackActivity extends AppCompatActivity {

    private EditText editText8;
    private EditText editText12;
    private Button button9;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().setTitle("意见反馈");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText8 = findViewById(R.id.editText8);
        editText12 = findViewById(R.id.editText12);
        button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIService service = RetrofitManager.getRetrofit().create(APIService.class);
                Map<String, String> map = new HashMap<>();
                map.put("content", editText12.getText().toString());
                map.put("title", editText8.getText().toString());
                RequestBody body = RequestBody.create(MediaType.parse("application/json"), new JSONObject(map).toString());
                Call<ModifyPassword> call = service.postFeedback(MainActivity.token, body);
                call.enqueue(new Callback<ModifyPassword>() {
                    @Override
                    public void onResponse(Call<ModifyPassword> call, Response<ModifyPassword> response) {
                        if (response.body().getMsg().equals("操作成功"))
                            Toast.makeText(FeedbackActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(FeedbackActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ModifyPassword> call, Throwable t) {

                    }
                });
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