package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.bean.ModifyPersonalInfo;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalInfoActivity extends AppCompatActivity {

    private EditText editText6;
    private EditText editText9;
    private EditText editText10;
    private EditText editText11;
    private Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        getSupportActionBar().setTitle("个人信息");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText6 = findViewById(R.id.editText6);
        editText9 = findViewById(R.id.editText9);
        editText10 = findViewById(R.id.editText10);
        editText11 = findViewById(R.id.editText11);
        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap<>();
                map.put("nickName", editText6.getText().toString());
                map.put("avatar", editText9.getText().toString());
                map.put("phonenumber", editText11.getText().toString());
                map.put("sex", editText10.getText().toString());
                RequestBody body = RequestBody.create(MediaType.parse("application/json"), new JSONObject(map).toString());
                APIService service = RetrofitManager.getRetrofit().create(APIService.class);
                Call<ModifyPersonalInfo> call = service.modifyPersonalInfo(MainActivity.token, body);
                call.enqueue(new Callback<ModifyPersonalInfo>() {
                    @Override
                    public void onResponse(Call<ModifyPersonalInfo> call, Response<ModifyPersonalInfo> response) {
                        Toast.makeText(PersonalInfoActivity.this, "修改成功。", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ModifyPersonalInfo> call, Throwable t) {
                        Toast.makeText(PersonalInfoActivity.this, "修改失败。", Toast.LENGTH_SHORT).show();
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