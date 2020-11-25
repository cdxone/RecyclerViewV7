package com.example.recyclerviewv7.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.recyclerviewv7.R;
import com.example.recyclerviewv7.adapter.SwipeRefreshAdapter;

import java.util.ArrayList;

public class SwipeRefreshActivity extends AppCompatActivity {

    private Context mContext;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private ArrayList<String> mData = new ArrayList<>();
    private SwipeRefreshAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);
        initParamsAndValues();
        initView();
        initAdapter();
        initRefreshLayout();
        initData();
    }

    private void initRefreshLayout() {
        //#DE474B
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.red));
        mSwipeRefreshLayout.setProgressViewOffset(true,400,600);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(mContext, "下载刷新", Toast.LENGTH_SHORT).show();
                refreshData();
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });
    }

    private void refreshData() {
        mData.clear();
        for (int i = 0; i < 40; i++) {
            mData.add("Good,i:" + i);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void initAdapter() {
        mAdapter = new SwipeRefreshAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            mData.add("HelloWorld:" + i);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.rv_list);
        mSwipeRefreshLayout = findViewById(R.id.swipeLayout);
        int dp_10 = getResources().getDimensionPixelSize(R.dimen.dp_10);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(mContext));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    private void initParamsAndValues() {
        mContext = this;
    }

    /**
     * 间距的一个辅助类
     */
    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;//每个条目的间距
        private int margin;//左右两边的边距

        public SpacesItemDecoration(Context context) {
            this.margin = context.getResources().getDimensionPixelSize(R.dimen.dp_15);
            this.space = context.getResources().getDimensionPixelSize(R.dimen.dp_10);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = margin;
            outRect.right = margin;
            outRect.bottom = space;
            // 如果是第一个元素，顶部加入边距
            if (parent.getChildPosition(view) == 0)
                outRect.top = space;
        }
    }
}