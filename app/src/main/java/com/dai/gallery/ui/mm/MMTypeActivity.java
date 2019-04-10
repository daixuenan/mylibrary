package com.dai.gallery.ui.mm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.dai.gallery.R;
import com.dai.gallery.adapter.HomeMMAdapter;
import com.dai.gallery.bean.HomeListBean;
import com.dai.mylibrary.bean.FileBean;
import com.dai.gallery.bean.MMTypeBean;
import com.dai.gallery.network.ApiGetRequest;
import com.dai.gallery.network.base.BasePageBean;
import com.dai.gallery.network.interfaces.NetWorkCallBack;
import com.dai.gallery.ui.base.BaseRefreshActivity;
import com.dai.mylibrary.utils.DpPxSpUtils;
import com.dai.mylibrary.widget.RecyclerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import me.dkzwm.smoothrefreshlayout.RefreshingListenerAdapter;

public class MMTypeActivity extends BaseRefreshActivity {

    private MMTypeBean type;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private HomeMMAdapter homeMMAdapter;
    private List<FileBean> dataList;

    private BasePageBean basePageBean;

    public static void start(Context context, MMTypeBean type) {
        Intent intent = new Intent(context, MMTypeActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mm_type;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        loadData(true);
    }

    private void init() {
        type = (MMTypeBean) getIntent().getSerializableExtra("type");
        setTitle(type.getDescription());

        basePageBean = new BasePageBean();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new RecyclerItemDecoration(DpPxSpUtils.dip2px(15), DpPxSpUtils.dip2px(15), 2));

        homeMMAdapter = new HomeMMAdapter(mContext, HomeListBean.class);
        recyclerView.setAdapter(homeMMAdapter);

        setRefreshListener(new RefreshingListenerAdapter() {
            @Override
            public void onRefreshBegin(boolean isRefresh) {
                loadData(isRefresh);
            }
        });
    }

    private void loadData(boolean isRefresh) {
        if (isRefresh) {
            dataList = new ArrayList<>();
            basePageBean.setPageNum(1);
        } else {
            basePageBean.setPageNum(basePageBean.getPageNum() + 1);
        }
        ApiGetRequest.getMMByType(type.getId(), basePageBean.getPageNum(), new NetWorkCallBack() {
            @Override
            public void onResponse(boolean isSucceed, int status, String response) {
                if (isSucceed) {
                    List<FileBean> mmList = JSON.parseArray(response, FileBean.class);
                    dataList.addAll(mmList);
                    homeMMAdapter.refresh(dataList);
                }
            }
        });
    }
}
