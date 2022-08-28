package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myapplication.APIService;
import com.example.myapplication.FeedbackActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.ModifyPasswordActivity;
import com.example.myapplication.OrderActivity;
import com.example.myapplication.PersonalInfoActivity;
import com.example.myapplication.R;
import com.example.myapplication.RetrofitManager;
import com.example.myapplication.bean.PersonalInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment5 extends Fragment {

    private ImageView imageView6;
    private TextView textView7;
    private ListView listView23;
    private Button button7;
    private boolean isLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_5, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView6 = view.findViewById(R.id.imageView6);
        textView7 = view.findViewById(R.id.textView7);
        listView23 = view.findViewById(R.id.listView23);
        isLogin = false;
        button7 = view.findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLogin = false;
                textView7.setText("");
                imageView6.setBackgroundResource(0);
            }
        });
        List<String> list = new ArrayList<>();
        list.add("个人信息");
        list.add("订单列表");
        list.add("修改密码");
        list.add("意见反馈");
        listView23.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list));
        listView23.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (isLogin)
                            startActivity(new Intent(getActivity(), PersonalInfoActivity.class));
                        else
                            Toast.makeText(getActivity(), "请先登录！", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        if (isLogin)
                            startActivity(new Intent(getActivity(), OrderActivity.class));
                        else
                            Toast.makeText(getActivity(), "请先登录！", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        if (isLogin)
                            startActivity(new Intent(getActivity(), ModifyPasswordActivity.class));
                        else
                            Toast.makeText(getActivity(), "请先登录！", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        if (isLogin)
                            startActivity(new Intent(getActivity(), FeedbackActivity.class));
                        else
                            Toast.makeText(getActivity(), "请先登录！", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        APIService service = RetrofitManager.getRetrofit().create(APIService.class);
        Call<PersonalInfo> call = service.getPersonalInfo(MainActivity.token);
        call.enqueue(new Callback<PersonalInfo>() {
            @Override
            public void onResponse(Call<PersonalInfo> call, Response<PersonalInfo> response) {
                isLogin = true;
                textView7.setText(response.body().getUser().getNickName());
                Glide.with(getActivity())
                        .load("http://218.7.112.123:10001"
                                + response.body().getUser().getAvatar())
                        .into(imageView6);
            }

            @Override
            public void onFailure(Call<PersonalInfo> call, Throwable t) {

            }
        });
    }
}
