package com.dai.gallery.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.dkzwm.smoothrefreshlayout.SmoothRefreshLayout;
import me.dkzwm.smoothrefreshlayout.extra.footer.ClassicFooter;
import me.dkzwm.smoothrefreshlayout.extra.header.ClassicHeader;

/**
 * Created by dai on 2017/11/22.
 */

public abstract class BaseRefreshFragment extends BaseFragment {

    protected SmoothRefreshLayout refreshLayout;

    //layoutId
    protected abstract int getLayoutResId();

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        super.startActivity(intent, options);
    }

    //数据为空
    private LinearLayout linearNoData;
    private ImageView imgNoData;
    private TextView tvNoData;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initRefreshLayout();
        initNoData();
    }

    /**
     * 初始化刷新加载
     */
    private void initRefreshLayout() {
        refreshLayout = (SmoothRefreshLayout) rootView.findViewById(com.dai.mylibrary.R.id.refreshLayout);
        if (refreshLayout == null) {
            return;
        }
        refreshLayout.setMode(SmoothRefreshLayout.MODE_BOTH);
        refreshLayout.setHeaderView(new ClassicHeader(getActivity()));
        refreshLayout.setFooterView(new ClassicFooter(getActivity()));
    }

    //设置刷新加载监听
    protected void setRefreshListener(final SmoothRefreshLayout.OnRefreshListener onRefreshListener) {
        if (refreshLayout == null || onRefreshListener == null) {
            return;
        }
        refreshLayout.setOnRefreshListener(new SmoothRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefreshBegin(final boolean isRefresh) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onRefreshListener.onRefreshBegin(isRefresh);
                        refreshLayout.refreshComplete();
                    }
                });
            }

            @Override
            public void onRefreshComplete() {
            }
        });
    }

    /**
     * 初始化数据为空视图
     */
    private void initNoData() {
        linearNoData = (LinearLayout) rootView.findViewById(com.dai.mylibrary.R.id.linear_no_data);
        imgNoData = (ImageView) rootView.findViewById(com.dai.mylibrary.R.id.img_no_data);
        tvNoData = (TextView) rootView.findViewById(com.dai.mylibrary.R.id.tv_no_data);
    }

    //设置是否为空数据
    protected void setNoData(boolean isNoData) {
        if (linearNoData != null) {
            linearNoData.setVisibility(isNoData ? View.VISIBLE : View.GONE);
        }
    }

    //设置空数据图片
    protected void setNoDataDrawable(int imgResId) {
        if (imgNoData != null) {
            imgNoData.setImageResource(imgResId);
        }
    }

    //设置空数据文本
    protected void setNoDataText(String text) {
        if (tvNoData != null) {
            tvNoData.setText(text);
        }
    }

}
