package com.dai.gallery.utils;

import android.content.Context;

import com.dai.gallery.AppApplication;
import com.dai.mylibrary.utils.CacheUtils;
import com.dai.mylibrary.utils.app.AppUtils;
import com.dai.mylibrary.utils.app.DeviceUtils;

/**
 * Created by dai on 2018/11/19.
 */

public class Configure {

    public static void init(Context context) {
        APP_VERSION = AppUtils.getAppVersion(context);
        SCREEN_WIDTH = DeviceUtils.getScreenWidth(context);
        SCREEN_HEIGHT = DeviceUtils.getScreenHeight(context);
        DEVICE_TOKEN = CacheUtils.getString(context, CacheUtils.DEVICE_TOKEN);
        TOKEN = CacheUtils.getUserString(context, CacheUtils.TOKEN);
    }

    /**
     * APP
     */

    //APP version
    public static String APP_VERSION = "1.1.0";

    //APP token
    public static String TOKEN = "";

    //APP deviceToken
    public static String DEVICE_TOKEN = "";

    //device 1android 2iOS
    public static final int DEVICE_TYPE = 1;

    //屏幕宽高
    public static int SCREEN_WIDTH = 1080;
    public static int SCREEN_HEIGHT = 1920;

    /**
     * NetWork
     */

    public final static boolean isDebug = AppUtils.isDebug(AppApplication.getInstance());

    //release
    public final static String HOST_RELEASE = "http://94.191.5.138:8080";

    //QA
//    public final static String HOST_TEST = "https://www.youpinying.com/customer-api";
    //debug
//    public final static String HOST_TEST = "http://test.xinjucai.com:8282";
//    public final static String HOST_TEST = "http://172.168.10.9:10086";
    public final static String HOST_TEST = "http://172.168.10.15:8080";
//    public final static String HOST_TEST = "http://47.101.210.191:10086";
    //沈业翔
//    public final static String HOST_TEST = "http://172.168.10.1:10086";
//    public final static String HOST_TEST = "http://dev.xinjucai.com/axinjucai";

    //方俊
//    public final static String HOST_TEST = "http://172.168.10.103:8089";
    //邱天佳
//    public final static String HOST_TEST = "http://172.168.10.105:8082";

    public final static String HOST = isDebug ? HOST_TEST : HOST_RELEASE;

    /**
     * file
     */
    public final static String APP_FOLDER = AppUtils.getStoragePath() + "/youpinying/";
}
