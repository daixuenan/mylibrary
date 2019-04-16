package com.dai.gallery.ui.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.dai.gallery.R;
import com.dai.gallery.adapter.PageFragmentAdapter;
import com.dai.gallery.ui.base.BaseFragment;
import com.dai.gallery.ui.base.BaseRefreshFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class TestFragment extends BaseRefreshFragment {

    @Bind(R.id.viewPager)
    VerticalViewPager viewPager;
    private TestAdapter testAdapter;

    private PageFragmentAdapter pageFragmentAdapter;

    public static TestFragment newInstant() {
        TestFragment fragment = new TestFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frame_test;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
        List<BaseFragment> pageList = new ArrayList<>();
        pageList.add(new Fragment1());
        pageList.add(new Fragment2());
        pageFragmentAdapter = new PageFragmentAdapter(getChildFragmentManager(), pageList);
        viewPager.setOffscreenPageLimit(pageList.size());
        viewPager.setAdapter(pageFragmentAdapter);
    }
}
