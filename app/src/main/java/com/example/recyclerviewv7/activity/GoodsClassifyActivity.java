package com.example.recyclerviewv7.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.recyclerviewv7.R;

public class GoodsClassifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_classify);
        initView();
    }

    private void initView() {
         rvClassify = findViewById(R.id.rl_classify);
    }
}