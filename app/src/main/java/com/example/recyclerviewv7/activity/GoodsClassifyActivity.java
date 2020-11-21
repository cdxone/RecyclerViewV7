package com.example.recyclerviewv7.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.recyclerviewv7.R;
import com.example.recyclerviewv7.adapter.LeftAdapter;
import com.example.recyclerviewv7.adapter.RightAdapter;
import com.example.recyclerviewv7.mock.TestData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 商品分类页面
 */
public class GoodsClassifyActivity extends AppCompatActivity  {

    private RecyclerView mRvLeft;//左侧RecyclerView
    private RecyclerView mRvRight;//右侧RecyclerView

    private Context mContext;
    private ArrayList<String> mLeftList = new ArrayList<>();
    private ArrayList<String> mRightList = new ArrayList<>();
    private HashMap<String,ArrayList<String>> mMapData = new HashMap<>();
    private LeftAdapter mLeftAdapter;
    private RightAdapter mRightAdapter;
    private JSONArray mObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_classify);
        initParamsAndValues();
        initView();
        initData();
    }

    private void initParamsAndValues() {
        mContext = this;
    }

    private void initView() {
        mRvLeft = findViewById(R.id.rv_left);
        mRvRight = findViewById(R.id.rv_right);

        mRvLeft.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        mLeftAdapter = new LeftAdapter(mLeftList);
        mLeftAdapter.setOnItemChildClickListener(mLeftListener);
        mRvLeft.setAdapter(mLeftAdapter);

        mRvRight.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        mRightAdapter = new RightAdapter(mRightList);
        mRightAdapter.setOnItemChildClickListener(mRightListener);
        mRvRight.setAdapter(mRightAdapter);
    }

    private void initData() {
        mObjects = JSON.parseArray(TestData.classifyData);
        for (int i = 0; i < mObjects.size(); i++) {
            JSONObject obj = mObjects.getJSONObject(i);
            String key = obj.getString("key");
            mLeftList.add(key);

            ArrayList<String> list = new ArrayList<>();
            JSONArray data = obj.getJSONArray("data");
            for (int j = 0; j < data.size(); j++) {
                list.add(data.getString(j));
            }
            mMapData.put(key,list);
        }
        mLeftAdapter.notifyDataSetChanged();

        mRightList.clear();
        if (mObjects.size() > 0){
            String key = mObjects.getJSONObject(0).getString("key");
            ArrayList<String> list = mMapData.get(key);
            mRightList.addAll(list);
        }
        mLeftAdapter.notifyDataSetChanged();
    }

    BaseQuickAdapter.OnItemChildClickListener mLeftListener = new BaseQuickAdapter.OnItemChildClickListener() {
        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            if (view.getId() == R.id.tv_item){
                Toast.makeText(mContext, "11" + adapter.getData().get(position).toString(), Toast.LENGTH_SHORT).show();
                String key = (String) adapter.getItem(position);
                ArrayList<String> list = mMapData.get(key);
                mRightAdapter.setNewData(list);
            }
        }
    };


    BaseQuickAdapter.OnItemChildClickListener mRightListener = new BaseQuickAdapter.OnItemChildClickListener() {
        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            if (view.getId() == R.id.tv_item){
                Toast.makeText(mContext, adapter.getData().get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        }
    };
}