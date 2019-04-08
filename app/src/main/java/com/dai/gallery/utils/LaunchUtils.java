package com.dai.gallery.utils;

import android.content.Context;
import android.text.TextUtils;

import com.dai.mylibrary.bean.MenuBean;
import com.dai.mylibrary.utils.arouter.ARouterUtlis;

public class LaunchUtils {

    public static void start(Context context, MenuBean bean) {
        if (bean == null) {
            return;
        }
        if (!TextUtils.isEmpty(bean.getUrl()) && TextUtils.isEmpty(bean.getCode())) {
//            H5Activity.start(context, bean.getUrl());
        } else if (TextUtils.isEmpty(bean.getUrl()) && !TextUtils.isEmpty(bean.getCode())) {
//            start(bean.getNativeUrl());
            //FIXME 先写死 路由框架需要调试
//            if (ARouterConstants.ROUTER_BANKCARD.equals(bean.getNativeUrl())) {
//                MyBankCardActivity.start(context);
//            } else if (ARouterConstants.ROUTER_COMMISSION.equals(bean.getNativeUrl())) {
//                MyCommissionActivity.start(context);
//            } else if (ARouterConstants.ROUTER_ACHIEVEMENT.equals(bean.getNativeUrl())) {
//                MyAchievementActivity.start(context);
//            }
        }
    }

    public static void start(String appKey) {
        if (TextUtils.isEmpty(appKey)) {
            return;
        }
        ARouterUtlis.getInstance().build(appKey).launch();
    }

}
