package com.dai.mylibrary.widget.dialogs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dai.mylibrary.R;
import com.dai.mylibrary.widget.dialogs.base.BaseDialog;

public class TipsTextDialog extends BaseDialog {

    public TipsTextDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getDialogLayout() {
        return R.layout.dialog_tips_text;
    }

    private TextView tvText;
    private Button btnOk;

    @Override
    public void init() {
        tvText = (TextView) findViewById(R.id.tvText);
        btnOk = (Button) findViewById(R.id.btnOk);
    }

    @Override
    public void showDialog() {
        if (!TextUtils.isEmpty(dialogBean.getContent())) {
            tvText.setText(dialogBean.getContent());
        }
        if (!TextUtils.isEmpty(dialogBean.getBtnTextOk())) {
            btnOk.setText(dialogBean.getBtnTextOk());
        }
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onDialogSelectListener != null) {
                    onDialogSelectListener.onOkClicked();
                }
            }
        });
        show();
    }
}