package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class ModifyPasswordActivity extends AppCompatActivity {

    private EditText editText5;
    private TextView textView27;
    private TextView textView26;
    private EditText editText7;
    private Button button8;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        getSupportActionBar().setTitle("修改密码");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText5 = findViewById(R.id.editText5);
        textView27 = findViewById(R.id.textView27);
        textView26 = findViewById(R.id.textView26);
        editText7 = findViewById(R.id.editText7);
        button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIService service = RetrofitManager.getRetrofit().create(APIService.class);
                Map<String, String> map = new HashMap<>();
                map.put("newPassword", editText5.getText().toString());
                map.put("oldPassword", editText7.getText().toString());
                RequestBody body = RequestBody.create(MediaType.parse("application/json"), new JSONObject(map).toString());
                Call<ModifyPassword> call = service.modifyPassword(MainActivity.token, body);
                call.enqueue(new Callback<ModifyPassword>() {
                    @Override
                    public void onResponse(Call<ModifyPassword> call, Response<ModifyPassword> response) {
                        if (response.body().getMsg().equals("操作成功"))
                            Toast.makeText(ModifyPasswordActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(ModifyPasswordActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
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