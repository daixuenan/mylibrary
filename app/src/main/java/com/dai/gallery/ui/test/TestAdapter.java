package com.dai.gallery.ui.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dai.gallery.R;
import com.dai.mylibrary.adapter.base.BaseRecyclerViewAdapter;
import com.dai.mylibrary.adapter.base.BaseRecyclerViewHolder;

import butterknife.ButterKnife;

public class TestAdapter extends BaseRecyclerViewAdapter {

    public TestAdapter(Context _ctx, Class cls) {
        super(_ctx, cls);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateView(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.frame_test1, parent, false));
    }

    @Override
    protected void onBindView(BaseRecyclerViewHolder holder, int position) {
    }

    static class ViewHolder extends BaseRecyclerViewHolder {

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
