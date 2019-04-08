package com.dai.gallery;

import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dai.gallery.utils.AccountUtils;
import com.dai.gallery.utils.Configure;
import com.dai.gallery.utils.push.PushActions;
import com.dai.mylibrary.utils.CacheUtils;
import com.dai.mylibrary.utils.DpPxSpUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UHandler;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AppApplication extends MultiDexApplication {

    private static AppApplication mInstance;

    public static synchronized AppApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        mInstance = this;

        //version
        Configure.init(this);

        //DpUtils
        DpPxSpUtils.init(this.getResources().getDisplayMetrics().density);

        //ARouter
        ARouter.init(this);

        //umeng初始化
        initUMENG();

        //关闭部分机型异常api系统弹弹窗
        closeAndroidPDialog();
    }

    private void initUMENG() {
        UMConfigure.setLogEnabled(Configure.isDebug);

        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, getString(R.string.UMENG_MESSAGE_SECERT));

        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.MANUAL);
        //社会化分享
//        PlatformConfig.setWeixin(getString(R.string.WECHAT_APPID), null);
//        PlatformConfig.setQQZone("", "");
//        PlatformConfig.setSinaWeibo("", "", "");

//        //推送
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Configure.DEVICE_TOKEN = deviceToken;
                CacheUtils.setString(getInstance(), CacheUtils.DEVICE_TOKEN, deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e("umeng push error: ", s + "\t" + s1);
            }
        });
        mPushAgent.setMessageHandler(new UmengMessageHandler() {
            @Override
            public void dealWithNotificationMessage(Context context, UMessage uMessage) {
                super.dealWithNotificationMessage(context, uMessage);
                if (!TextUtils.isEmpty(uMessage.custom)) {
                    JSONObject pushObject = JSON.parseObject(uMessage.custom);
                    String action = pushObject.getString("action");
                    if (PushActions.ACTION_LOGOUT.equals(action)) {
                        AccountUtils.doLogout(getInstance());
                    }
                }
            }
        });
        mPushAgent.setNotificationClickHandler(new UHandler() {
            @Override
            public void handleMessage(Context context, UMessage uMessage) {
                if (!TextUtils.isEmpty(uMessage.url)) {
//                    H5Activity.startNewTask(getInstance(), uMessage.url);
                }
            }
        });
    }

    //关闭系统弹框
    private void closeAndroidPDialog() {
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
