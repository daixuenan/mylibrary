package com.dai.mylibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dai.mylibrary.R;
import com.dai.mylibrary.adapter.base.BaseRecyclerViewAdapter;
import com.dai.mylibrary.adapter.base.BaseRecyclerViewHolder;
import com.dai.mylibrary.bean.LabelBean;

public class LabelAdapter extends BaseRecyclerViewAdapter {

    public LabelAdapter(Context _ctx, Class cls) {
        super(_ctx, cls);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateView(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_label, parent, false));
    }

    @Override
    protected void onBindView(BaseRecyclerViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;

        final LabelBean bean = (LabelBean) dataList.get(position);

        viewHolder.tvLabel.setText(bean.getLabel());
        viewHolder.tvContent.setText(bean.getContent());
    }

    static class ViewHolder extends BaseRecyclerViewHolder {

        TextView tvLabel;
        TextView tvContent;

        ViewHolder(View view) {
            super(view);
            tvLabel = view.findViewById(R.id.tvLabel);
            tvContent = view.findViewById(R.id.tvContent);
        }
    }

}
