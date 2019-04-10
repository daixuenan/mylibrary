package com.dai.gallery.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.dai.gallery.R;
import com.dai.gallery.adapter.HomeListAdapter;
import com.dai.gallery.bean.HomeListBean;
import com.dai.mylibrary.bean.FileBean;
import com.dai.gallery.network.ApiGetRequest;
import com.dai.gallery.network.interfaces.NetWorkCallBack;
import com.dai.gallery.ui.base.BaseRefreshFragment;
import com.dai.gallery.utils.Configure;
import com.dai.mylibrary.utils.image.ImageUtils;
import com.dai.mylibrary.widget.photoview.ViewImageActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.bingoogolapple.bgabanner.BGABanner;

public class HomeFragment extends BaseRefreshFragment {

    @Bind(R.id.banner)
    BGABanner banner;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private HomeListAdapter homeListAdapter;
    private List<HomeListBean> dataList;

    public static HomeFragment newInstant() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frame_home;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initBanner();
        initHomeList();

        loadBanner();
        loadHomeList();
    }

    private void initBanner() {
        LinearLayout.LayoutParams bannerParams = (LinearLayout.LayoutParams) banner.getLayoutParams();
        bannerParams.height = Configure.SCREEN_WIDTH / 2;
        banner.setLayoutParams(bannerParams);
        banner.setAdapter(bgAdapter);
        banner.setDelegate(delegate);
    }

    private void initHomeList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        homeListAdapter = new HomeListAdapter(activity, HomeListBean.class);
        recyclerView.setAdapter(homeListAdapter);
    }

    private void loadBanner() {
        ApiGetRequest.getBanner(new NetWorkCallBack() {
            @Override
            public void onResponse(boolean isSucceed, int status, String response) {
                if (isSucceed) {
                    List<FileBean> bannerList = JSON.parseArray(response, FileBean.class);
                    if (bannerList != null && bannerList.size() > 0) {
                        List<String> tips = new ArrayList<>();
                        for (FileBean mmBean : bannerList) {
                            tips.add(mmBean.getDescription());
                        }
                        banner.setData(bannerList, tips);
                    }
                }
            }
        });
    }

    private void loadHomeList() {
        dataList = new ArrayList<>();
        ApiGetRequest.getHome(new NetWorkCallBack() {
            @Override
            public void onResponse(boolean isSucceed, int status, String response) {
                if (isSucceed) {
                    List<HomeListBean> homeListBeans = JSON.parseArray(response, HomeListBean.class);
                    dataList.addAll(homeListBeans);
                    homeListAdapter.refresh(dataList);
                }
            }
        });
    }

    private BGABanner.Adapter bgAdapter = new BGABanner.Adapter() {
        @Override
        public void fillBannerItem(BGABanner banner, View itemView, @Nullable Object model, int position) {
            if (model instanceof FileBean) {
                FileBean mmBean = (FileBean) model;
                ImageUtils.loadMMImage(activity, mmBean.getDownloadurl(), mmBean.getRefer(), mmBean.getUseragent(), (ImageView) itemView);
            }
        }
    };

    private BGABanner.Delegate delegate = new BGABanner.Delegate() {
        @Override
        public void onBannerItemClick(BGABanner banner, View itemView, @Nullable Object model, int position) {
            if (model instanceof FileBean) {
                ApiGetRequest.getMMDetailList(((FileBean) model).getFoldername(), new NetWorkCallBack() {
                    @Override
                    public void onResponse(boolean isSucceed, int status, String response) {
                        if (isSucceed) {
                            List<FileBean> detailList = JSON.parseArray(response, FileBean.class);
                            ViewImageActivity.start(activity, detailList, 0);
                        }
                    }
                });
            }
        }
    };
}
