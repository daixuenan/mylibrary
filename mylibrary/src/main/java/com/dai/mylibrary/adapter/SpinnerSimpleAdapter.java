package com.dai.mylibrary.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dai.mylibrary.R;
import com.dai.mylibrary.bean.SpinnerBean;

import java.util.ArrayList;
import java.util.List;

public class SpinnerSimpleAdapter extends BaseAdapter {

    private Context ctx;
    private List<SpinnerBean> dataList;

    public SpinnerSimpleAdapter(Context ctx) {
        this.ctx = ctx;
        this.dataList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(ctx, R.layout.item_spinner_simple, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();// get convertView's holder

        holder.tvText.setText("测试文本");

        return convertView;
    }

    class ViewHolder {

        TextView tvText;

        public ViewHolder(View convertView) {
            tvText = (TextView) convertView.findViewById(R.id.tvText);
            convertView.setTag(this);
        }
    }

    public void refresh(List<SpinnerBean> dataList) {
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        this.dataList = dataList;
        notifyDataSetChanged();
    }

}
