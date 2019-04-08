package com.dai.gallery.utils;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.dai.gallery.AppApplication;
import com.dai.gallery.network.ApiGetRequest;
import com.dai.gallery.network.interfaces.NetWorkCallBack;
import com.dai.gallery.utils.eventbus.EventMessage;
import com.dai.gallery.utils.eventbus.EventType;
import com.dai.mylibrary.bean.UserBean;
import com.dai.mylibrary.utils.CacheUtils;

import org.greenrobot.eventbus.EventBus;

public class AccountUtils {

    //更新用户信息
    public static void updateUserInfo() {
        ApiGetRequest.getUserInfo(new NetWorkCallBack() {
            @Override
            public void onResponse(boolean isSucceed, int status, String response) {
                if (isSucceed) {
                    UserBean userBean = JSON.parseObject(response, UserBean.class);
                    setUserInfo(userBean);

                    //通知更新用户信息
                    EventBus.getDefault().post(new EventMessage(EventType.EVENT_MESSAGE_UPDATE_USER_INFO));
                } else {
                    Toast.makeText(AppApplication.getInstance(), response, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void setUserInfo(UserBean userBean) {
        CacheUtils.setUserString(AppApplication.getInstance(), CacheUtils.USER_INFO, JSON.toJSONString(userBean));
    }

    public static UserBean getUserInfo() {
        String userStr = CacheUtils.getUserString(AppApplication.getInstance(), CacheUtils.USER_INFO);
        UserBean userBean = null;
        try {
            userBean = JSON.parseObject(userStr, UserBean.class);
            if (userBean == null) {
                userBean = new UserBean();
            }
        } catch (Exception e) {
            userBean = new UserBean();
        }
        return userBean;
    }

    public static void doLogout(Context context) {
        CacheUtils.clearUserCache(context);
        Configure.TOKEN = "";

        EventBus.getDefault().post(new EventMessage(EventType.EVENT_MESSAGE_UPDATE_USER_INFO));
    }
}
