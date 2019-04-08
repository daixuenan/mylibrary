package com.dai.mylibrary.utils.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import java.io.Serializable;

/**
 * Created by dai on 2018/11/19.
 */

public class AppUtils implements Serializable {

    //app folder
    public static final String getStoragePath() {
        return Environment.getExternalStorageState();
    }

    public static boolean isDebug(Context context) {
        return (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    public static String getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "1.1.0";
    }
}
