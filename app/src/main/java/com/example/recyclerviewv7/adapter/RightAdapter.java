package com.example.recyclerviewv7.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.recyclerviewv7.R;

import java.util.List;

public class RightAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RightAdapter(@Nullable List<String> data) {
        super(R.layout.layout_right_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item, item).addOnClickListener(R.id.tv_item);
    }
}
