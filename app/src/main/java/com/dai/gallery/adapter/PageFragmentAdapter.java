package com.dai.gallery.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dai.gallery.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yun on 2017/3/7.
 */

public class PageFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragmentList;

    public PageFragmentAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void refresh(List<BaseFragment> fragmentList) {
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
        this.fragmentList = fragmentList;
        notifyDataSetChanged();
    }

    public boolean onBackPressed() {
        boolean isBack = true;
        return isBack;
    }

}
