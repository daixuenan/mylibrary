package com.dai.gallery.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.dkzwm.smoothrefreshlayout.RefreshingListenerAdapter;
import me.dkzwm.smoothrefreshlayout.SmoothRefreshLayout;
import me.dkzwm.smoothrefreshlayout.extra.footer.ClassicFooter;
import me.dkzwm.smoothrefreshlayout.extra.header.ClassicHeader;

/**
 * Created by dai on 2017/11/22.
 */

public abstract class BaseRefreshActivity extends BaseActivity {

    protected SmoothRefreshLayout refreshLayout;

    //数据为空
    private LinearLayout linearNoData;
    private ImageView imgNoData;
    private TextView tvNoData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initRefreshLayout();
        initNoData();
    }

    //初始化刷新加载
    private void initRefreshLayout() {
        refreshLayout = (SmoothRefreshLayout) findViewById(com.dai.mylibrary.R.id.refreshLayout);
        if (refreshLayout == null) {
            return;
        }
        refreshLayout.setMode(SmoothRefreshLayout.MODE_BOTH);
        refreshLayout.setHeaderView(new ClassicHeader(this));
        refreshLayout.setFooterView(new ClassicFooter(this));
    }

    //设置刷新加载监听
    protected void setRefreshListener(final RefreshingListenerAdapter refreshListener) {
        if (refreshLayout == null || refreshListener == null) {
            return;
        }
        refreshLayout.setOnRefreshListener(new RefreshingListenerAdapter() {
            @Override
            public void onRefreshBegin(final boolean isRefresh) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshListener.onRefreshBegin(isRefresh);
                        refreshLayout.refreshComplete();
                    }
                });
            }
        });
    }

    //初始化数据为空视图
    private void initNoData() {
        linearNoData = (LinearLayout) findViewById(com.dai.mylibrary.R.id.linear_no_data);
        imgNoData = (ImageView) findViewById(com.dai.mylibrary.R.id.img_no_data);
        tvNoData = (TextView) findViewById(com.dai.mylibrary.R.id.tv_no_data);
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
