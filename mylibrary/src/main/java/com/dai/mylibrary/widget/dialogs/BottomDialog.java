package com.dai.mylibrary.widget.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.dai.mylibrary.R;
import com.dai.mylibrary.utils.DpPxSpUtils;
import com.dai.mylibrary.widget.dialogs.base.BaseDialog;

public class BottomDialog extends BaseDialog {

    public BottomDialog(@NonNull Context context) {
        super(context);
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().getDecorView().setPadding(0, 0, 0, DpPxSpUtils.dip2px(61));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = screenWidth;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.dimAmount = 0;
        this.getWindow().setAttributes(params);
    }

    @Override
    public int getDialogLayout() {
        return R.layout.dialog_bottom;
    }

    private ImageView imgClose;
    private TextView tvContent;
    private TextView tvOk;

    @Override
    public void init() {
        imgClose = (ImageView) findViewById(R.id.imgClose);
        tvContent = (TextView) findViewById(R.id.tvContent);
        tvOk = (TextView) findViewById(R.id.tvOk);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (onDialogClosedListener != null) {
                    onDialogClosedListener.onClosed();
                }
            }
        });
    }

    @Override
    public void showDialog() {
        if (!TextUtils.isEmpty(dialogBean.getContent())) {
            tvContent.setText(dialogBean.getContent());
        }
        if (!TextUtils.isEmpty(dialogBean.getBtnTextOk())) {
            tvOk.setText(dialogBean.getBtnTextOk());
        }
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onDialogSelectListener != null) {
                    onDialogSelectListener.onOkClicked();
                }
            }
        });
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        show();
    }
}