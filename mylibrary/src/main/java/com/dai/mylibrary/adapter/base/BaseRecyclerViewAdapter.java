package com.dai.mylibrary.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.dai.mylibrary.adapter.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    protected List<T> dataList;

    private T selectedItem;

    protected LayoutInflater mInflater;
    protected Context ctx;

    private Class<T> cls;

    protected OnItemClickListener onItemClickListener;

    protected abstract BaseRecyclerViewHolder onCreateView(ViewGroup parent, int viewType);
    
    protected abstract void onBindView(BaseRecyclerViewHolder holder, int position);

    public BaseRecyclerViewAdapter(Context _ctx, Class<T> cls) {
        this.ctx = _ctx;
        mInflater = LayoutInflater.from(_ctx);
        this.dataList = new ArrayList<>();
        this.cls = cls;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateView(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        onBindView(holder, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public void setSelectedItem(T selectedItem) {
        this.selectedItem = selectedItem;
    }

    public T getBean(int position) {
        return dataList.get(position);
    }

    public T getSelectedItem() {
        return selectedItem;
    }

    //reload
    public void reloadItem(T oldT, T newT) {
        dataList.set(dataList.indexOf(oldT), newT);
        notifyDataSetChanged();
    }

    //refresh
    public void refresh(List<T> dataList) {
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<T> getDataList() {
        return JSON.parseArray(JSON.toJSONString(dataList), cls);
    }

}
