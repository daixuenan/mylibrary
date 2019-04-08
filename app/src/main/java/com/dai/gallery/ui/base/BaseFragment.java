package com.dai.gallery.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dai.gallery.R;

import butterknife.ButterKnife;

/**
 * Created by dai on 2017/11/22.
 */

public abstract class BaseFragment extends Fragment {

    protected Activity activity;

    protected View rootView;
    protected View bar;

    protected ImageView imgBackHead;
    protected TextView tvTitleHead;
    protected ImageView imgRightHead;
    protected ImageView imgLeft2Head;

    //layoutId
    protected abstract int getLayoutResId();

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        super.startActivity(intent, options);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutResId(), null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        activity = getActivity();

        initBar();
    }

    /**
     * 初始化标题栏
     */
    private void initBar() {
        bar = rootView.findViewById(R.id.bar);
        imgBackHead = (ImageView) rootView.findViewById(R.id.imgBackHead);
        tvTitleHead = (TextView) rootView.findViewById(R.id.tvTitleHead);
        imgRightHead = (ImageView) rootView.findViewById(R.id.imgRightHead);
        imgLeft2Head = (ImageView) rootView.findViewById(R.id.imgLeft2Head);

        setBackBtnVisiable(false);
    }

    //设置状态栏颜色
    protected void setBarColor(int colorId) {
        if (bar != null) {
            bar.setBackgroundColor(colorId);
        }
    }

    public String getTitle() {
        if (tvTitleHead != null) {
            return tvTitleHead.getText().toString();
        } else {
            return "";
        }
    }

    //设置返回按钮显影
    public void setBackBtnVisiable(boolean isVisiable) {
        if (imgBackHead != null) {
            imgBackHead.setVisibility(isVisiable ? View.VISIBLE : View.GONE);
        }
    }

    //设置右侧按钮监听
    public void setOnBackListener(View.OnClickListener onClickListener) {
        if (imgBackHead != null) {
            imgBackHead.setOnClickListener(onClickListener);
        }
    }

    //设置标题
    public void setTitle(String title) {
        if (tvTitleHead != null) {
            tvTitleHead.setText(title);
        }
    }

    //设置左边第二个图标显影
    protected void setLeft2BtnVisiable(boolean isVisiable) {
        if (imgLeft2Head != null) {
            imgLeft2Head.setVisibility(isVisiable ? View.VISIBLE : View.GONE);
        }
    }

    //设置左边第二个图标
    protected void setImgLeft2BtnImage(int resourceId) {
        if (imgLeft2Head != null) {
            imgLeft2Head.setImageResource(resourceId);
        }
    }

    //设置左边第二个监听
    protected void setOnLeft2BtnListener(View.OnClickListener onClickListener) {
        if (imgLeft2Head != null) {
            imgLeft2Head.setOnClickListener(onClickListener);
        }
    }

    //设置右侧按钮图标
    public void setRightImage(int resourceId) {
        if (imgRightHead != null) {
            imgRightHead.setImageResource(resourceId);
        }
    }

    //设置右侧按钮监听
    public void setOnRightImageListener(View.OnClickListener onClickListener) {
        if (imgRightHead != null) {
            imgRightHead.setOnClickListener(onClickListener);
        }
    }

    //设置右侧按钮显影
    public void setRightImageVisiable(boolean isVisiable) {
        if (imgRightHead != null) {
            imgRightHead.setVisibility(isVisiable ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 生命周期
     */

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(rootView);
    }
}