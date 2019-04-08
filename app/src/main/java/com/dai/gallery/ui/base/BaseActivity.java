package com.dai.gallery.ui.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dai.gallery.R;
import com.dai.mylibrary.utils.ActivityStackManager;
import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

/**
 * Created by dai on 2017/11/22.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected View barView;

    protected RelativeLayout header;
    protected View bgViewHead;
    protected TextView tvTitleHead;
    protected ImageView imgBackHead;
    protected ImageView imgLeft2Head;
    protected ImageView imgRightHead;
    protected ImageView imgRight2Head;

    protected abstract int getLayoutResId();

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        try {
            setContentView(getLayoutResId());
            //状态栏
            barView = LayoutInflater.from(this).inflate(R.layout.layout_status_bar, null);
            addContentView(barView, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.status_bar_height)));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getPackageName(), e.getMessage());
        }
        ButterKnife.bind(this);

        mContext = this;

        //透明状态栏隐藏顶部
        setShowStatusBar(true);
        setStatusBarColor(getResources().getColor(R.color.black));
        barView.setAlpha(0.2f);

        //当前Activity进栈
        ActivityStackManager.getInstance().pushActivity(this);

        //初始化页面接收ARouter参数
        ARouter.getInstance().inject(this);

        initBar();

        //initUmeng
//        PushAgent.getInstance(mContext).onAppStart();
    }

    //初始化标题栏
    private void initBar() {
        header = (RelativeLayout) findViewById(R.id.header);
        bgViewHead = findViewById(R.id.bgViewHead);
        tvTitleHead = (TextView) findViewById(R.id.tvTitleHead);
        imgBackHead = (ImageView) findViewById(R.id.imgBackHead);
        imgLeft2Head = (ImageView) findViewById(R.id.imgLeft2Head);
        imgRightHead = (ImageView) findViewById(R.id.imgRightHead);
        imgRight2Head = (ImageView) findViewById(R.id.imgRight2Head);
        if (imgBackHead != null) {
            imgBackHead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    //设置状态栏颜色
    protected void setStatusBarColor(int colorId) {
        if (barView != null) {
            barView.setBackgroundColor(colorId);
        }
    }

    //设置状态栏显示隐藏
    public void setShowStatusBar(boolean isVisibile) {
        getWindow().getDecorView().setFitsSystemWindows(isVisibile);
        if (isVisibile) {
            //透明状态栏 @顶部
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏 @底部 这一句不要加，目的是防止沉浸式状态栏和部分底部自带虚拟按键的手机（比如华为）发生冲突，注释掉就好了
            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                try {
                    Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
                    Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
                    field.setAccessible(true);
                    field.setInt(getWindow().getDecorView(), Color.TRANSPARENT);  //改为透明
                } catch (Exception e) {
                }
            }
        }
        barView.setVisibility(isVisibile ? View.VISIBLE : View.GONE);
        setFullScreen(!isVisibile);
    }

    //是否全屏（占用statusbar位置）
    public void setFullScreen(boolean isFullScreen) {
        int height = (int) getResources().getDimension(R.dimen.status_bar_height);
        if (isFullScreen) {
            height = 0;
        }
        if (((ViewGroup) findViewById(android.R.id.content)).getChildAt(0) != null) {
            ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0).setPadding(0, height, 0, 0);
        }
    }

    //设置标题
    protected void setTitle(String title) {
        if (tvTitleHead != null) {
            tvTitleHead.setText(title);
        }
    }

    //设置标题颜色
    public void setTitleColor(int resId) {
        if (tvTitleHead != null) {
            tvTitleHead.setTextColor(resId);
        }
    }

    //设置标题背景透明度
    public void setTitleBgAlpha(float alpha) {
        bgViewHead.setAlpha(alpha);
    }

    //设置返回监听
    protected void setOnBackListener(View.OnClickListener onClickListener) {
        if (imgBackHead != null) {
            imgBackHead.setOnClickListener(onClickListener);
        }
    }

    //设置back按钮显影
    protected void setBackBtnVisiable(boolean isVisiable) {
        if (imgBackHead != null) {
            imgBackHead.setVisibility(isVisiable ? View.VISIBLE : View.GONE);
        }
    }

    //设置返回按钮图标
    protected void setBackImage(int resourceId) {
        if (imgBackHead != null) {
            imgBackHead.setImageResource(resourceId);
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
    protected void setRightImage(int resourceId) {
        if (imgRightHead != null) {
            imgRightHead.setImageResource(resourceId);
        }
    }

    protected void setRightImage2(int resourceId) {
        if (imgRight2Head != null) {
            imgRight2Head.setImageResource(resourceId);
        }
    }

    //设置右侧按钮监听
    protected void setOnRightImageListener(View.OnClickListener onClickListener) {
        if (imgRightHead != null) {
            imgRightHead.setOnClickListener(onClickListener);
        }
    }

    //设置右侧按钮2监听
    protected void setOnRightImage2Listener(View.OnClickListener onClickListener) {
        if (imgRight2Head != null) {
            imgRight2Head.setOnClickListener(onClickListener);
        }
    }

    /**
     * 返回当前Activity布局文件的id
     *
     * @return
     */

//    @Override
//    public void startActivity(Intent intent) {
//        super.startActivity(intent);
//        overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
//    }
//
//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.close_enter, R.anim.close_exit);
//    }
//    @Override
//    public void startActivity(Intent intent) {
//        super.startActivity(intent);
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//    }
//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
//    }

    /**
     * 生命周期
     */

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Activity出栈
        ActivityStackManager.getInstance().popActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(mContext);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(mContext);
    }
}
