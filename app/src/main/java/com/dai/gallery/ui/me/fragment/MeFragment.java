package com.dai.gallery.ui.me.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dai.gallery.R;
import com.dai.gallery.ui.base.BaseRefreshFragment;

public class MeFragment extends BaseRefreshFragment {

    public static MeFragment newInstant() {
        MeFragment fragment = new MeFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frame_me;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
        //recyclerView
    }
}
