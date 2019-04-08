package com.dai.mylibrary.widget.dialogs;

import com.dai.mylibrary.widget.dialogs.base.BaseDialog;
import com.dai.mylibrary.widget.dialogs.interfaces.OnDialogClosedListener;
import com.dai.mylibrary.widget.dialogs.interfaces.OnDialogSelectListener;
import com.dai.mylibrary.widget.dialogs.interfaces.OnEditDialogDataListener;

public class DialogUtils {

    private volatile static DialogUtils instance = null;

    public static DialogUtils getInstance() {
        if (instance == null) {
            synchronized (DialogUtils.class) {
                if (instance == null) {
                    instance = new DialogUtils();
                }
            }
        }
        return instance;
    }

    private BaseDialog baseDialog;

    public DialogUtils setDialog(BaseDialog baseDialog) {
        this.baseDialog = baseDialog;
        return this;
    }

    //show
    public void show() {
        if (baseDialog != null) {
            baseDialog.showDialog();
        }
    }

    //参数编辑监听
    public DialogUtils setOnEditDialogDataListener(OnEditDialogDataListener onEditDialogDataListener) {
        if (baseDialog != null) {
            baseDialog.setOnEditDialogDataListener(onEditDialogDataListener);
        }
        return this;
    }

    //按钮监听
    public DialogUtils setOnDialogSelectListener(OnDialogSelectListener onDialogSelectListener) {
        if (baseDialog != null) {
            baseDialog.setOnDialogSelectListener(onDialogSelectListener);
        }
        return this;
    }

    //关闭
    public DialogUtils setOnDialogClosedListener(OnDialogClosedListener onDialogClosedListener) {
        if (baseDialog != null) {
            baseDialog.setOnDialogClosedListener(onDialogClosedListener);
        }
        return this;
    }
}
