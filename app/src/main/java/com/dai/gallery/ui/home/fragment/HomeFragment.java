package com.dai.gallery.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.dai.gallery.R;
import com.dai.gallery.bean.MMBean;
import com.dai.gallery.network.ApiGetRequest;
import com.dai.gallery.network.base.BasePageBean;
import com.dai.gallery.network.interfaces.NetWorkCallBack;
import com.dai.gallery.ui.base.BaseRefreshFragment;
import com.dai.gallery.utils.Configure;
import com.dai.mylibrary.utils.files.ImageUtils;

import java.util.List;

import butterknife.Bind;
import cn.bingoogolapple.bgabanner.BGABanner;

public class HomeFragment extends BaseRefreshFragment {

    @Bind(R.id.banner)
    BGABanner banner;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

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

        loadBanner();
    }

    private void initBanner() {
        LinearLayout.LayoutParams bannerParams = (LinearLayout.LayoutParams) banner.getLayoutParams();
        bannerParams.height = Configure.SCREEN_WIDTH / 2;
        banner.setLayoutParams(bannerParams);
        banner.setAdapter(bgAdapter);
        banner.setDelegate(delegate);
    }

    private void loadBanner() {
        ApiGetRequest.getBanner(new NetWorkCallBack() {
            @Override
            public void onResponse(boolean isSucceed, int status, String response) {
                if (isSucceed) {
                    BasePageBean basePageBean = JSON.parseObject(response, BasePageBean.class);
                    List<MMBean> bannerList = JSON.parseArray(basePageBean.getList().toString(), MMBean.class);
                    if (bannerList != null && bannerList.size() > 0) {
                        banner.setData(bannerList, null);
                    }
                }
            }
        });
    }

    private BGABanner.Adapter bgAdapter = new BGABanner.Adapter() {
        @Override
        public void fillBannerItem(BGABanner banner, View itemView, @Nullable Object model, int position) {
            if (model instanceof MMBean) {
                MMBean mmBean = (MMBean) model;
                ImageUtils.loadMMImage(activity, mmBean.getDownloadurl(), mmBean.getRefer(), mmBean.getUseragent(), (ImageView) itemView);
            }
        }
    };

    private BGABanner.Delegate delegate = new BGABanner.Delegate() {
        @Override
        public void onBannerItemClick(BGABanner banner, View itemView, @Nullable Object model, int position) {
            if (model instanceof MMBean) {
                //FIXME 跳转
            }
        }
    };
}
