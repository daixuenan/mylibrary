package com.dai.mylibrary.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by dai on 2017/11/29.
 */

public class ToastUtils {

    private static Toast toast;

    public static void toast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}
