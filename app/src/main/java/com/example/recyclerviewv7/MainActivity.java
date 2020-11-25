package com.example.recyclerviewv7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.recyclerviewv7.activity.GoodsClassifyActivity;
import com.example.recyclerviewv7.activity.SwipeRefreshActivity;

import java.util.ArrayList;

import apis.amapv2.com.listviewlibrary.activity.BaseListActivty;
import apis.amapv2.com.listviewlibrary.bean.ItemObject;

public class MainActivity extends BaseListActivty {

    @Override
    protected void addData(ArrayList<ItemObject> data) {
        data.add(new ItemObject("商品分类效果", GoodsClassifyActivity.class));
        data.add(new ItemObject("下拉刷新效果", SwipeRefreshActivity.class));
    }
}