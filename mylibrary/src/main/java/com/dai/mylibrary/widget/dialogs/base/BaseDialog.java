package com.dai.mylibrary.widget.dialogs.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.dai.mylibrary.R;
import com.dai.mylibrary.widget.dialogs.bean.DialogBean;
import com.dai.mylibrary.widget.dialogs.interfaces.OnDialogClosedListener;
import com.dai.mylibrary.widget.dialogs.interfaces.OnDialogSelectListener;
import com.dai.mylibrary.widget.dialogs.interfaces.OnEditDialogDataListener;

public abstract class BaseDialog extends Dialog {

    //宽度占屏幕比例
    public static final float RATIO_Width = 0.7f;

    protected Context mContext;

    //获取layoutID
    public abstract int getDialogLayout();

    //dialog编辑
    protected OnEditDialogDataListener onEditDialogDataListener;

    //dialog按钮点击
    protected OnDialogSelectListener onDialogSelectListener;

    //dialog关闭
    protected OnDialogClosedListener onDialogClosedListener;

    //dialog参数
    protected DialogBean dialogBean;

    //初始化dialog
    public abstract void init();

    //弹出dialog
    public abstract void showDialog();

    public BaseDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
        this.mContext = context;
        dialogBean = new DialogBean();
        setContentView(getDialogLayout());

        //初始化尺寸
        initView();

        //初始化控件
        init();
    }

    private void initView() {
        //获取屏幕信息
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = (int) (screenWidth * RATIO_Width);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        this.getWindow().setAttributes(params);
    }

    public void setOnEditDialogDataListener(OnEditDialogDataListener onEditDialogDataListener) {
        this.onEditDialogDataListener = onEditDialogDataListener;

        if (onEditDialogDataListener != null) {
            onEditDialogDataListener.onEditDialogData(dialogBean);
        }
    }

    public void setOnDialogSelectListener(OnDialogSelectListener onDialogSelectListener) {
        this.onDialogSelectListener = onDialogSelectListener;
    }

    public void setOnDialogClosedListener(OnDialogClosedListener onDialogClosedListener) {
        this.onDialogClosedListener = onDialogClosedListener;
    }
}
