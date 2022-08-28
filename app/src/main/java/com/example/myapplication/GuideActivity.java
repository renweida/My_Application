package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Guideline guideline;
    private RadioGroup radioGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.guide1, null);
        View view2 = inflater.inflate(R.layout.guide2, null);
        View view3 = inflater.inflate(R.layout.guide3, null);
        View view4 = inflater.inflate(R.layout.guide4, null);
        View view5 = inflater.inflate(R.layout.guide5, null);
        View view_dialog = inflater.inflate(R.layout.dialog, null);
        Button button = view5.findViewById(R.id.button);
        Button button2 = view5.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GuideActivity.this);
                builder.setTitle("网络设置")
                        .setView(view_dialog)
                        .setCancelable(false)
                        .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.e("asfasdfasdfasdf", "");
                                EditText editText = view_dialog.findViewById(R.id.editText);
                                EditText editText2 = view_dialog.findViewById(R.id.editText2);
                                String ip = editText.getText().toString();
                                String port = editText.getText().toString();
                                Log.e("asfasdfasdfasdf", ip + port);
                                SharedPreferences sp = getSharedPreferences("ip", Context.MODE_PRIVATE);
                                sp.edit().putString("ip", ip).apply();
                                sp.edit().putString("port", port).apply();
                            }
                        })
                        .setNegativeButton("保存", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.e("asfasdfasdfasdf", "");
                            }
                        })
                        .create()
                        .show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });

        List<View> list = new ArrayList<>();
        list.add(view1);
        list.add(view2);
        list.add(view3);
        list.add(view4);
        list.add(view5);
        viewPager.setAdapter(new PagerAdapter() {
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(list.get(position));
                return list.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(list.get(position));
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radioGroup.check(R.id.rb1);
                        break;
                    case 1:
                        radioGroup.check(R.id.rb2);
                        break;
                    case 2:
                        radioGroup.check(R.id.rb3);
                        break;
                    case 3:
                        radioGroup.check(R.id.rb4);
                        break;
                    case 4:
                        radioGroup.check(R.id.rb5);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        guideline = findViewById(R.id.guideline);
        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);
    }
}