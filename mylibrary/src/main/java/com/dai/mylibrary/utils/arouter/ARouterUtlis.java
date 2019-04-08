package com.dai.mylibrary.utils.arouter;

import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;

import java.io.Serializable;

public class ARouterUtlis {

    private volatile static ARouterUtlis instance = null;

    private String group;

    public static ARouterUtlis getInstance() {
        if (instance == null) {
            synchronized (ARouterUtlis.class) {
                if (instance == null) {
                    instance = new ARouterUtlis();
                }
            }
        }
        return instance;
    }

    private Postcard router;

    public ARouterUtlis() {
        setGroup(ARouterConstants.ROUTER_GROUP);
    }

    //set groups  before build() function
    public ARouterUtlis setGroup(String group) {
        this.group = group;
        return this;
    }

    //build
    public ARouterUtlis build(String page) {
        router = ARouter.getInstance().build("/" + group + "/" + page);
        return this;
    }

    //launch
    public void launch() {
        if (router != null) {
            router.navigation();
        }
    }

    //conflict with with(Bundle bundle)
    public ARouterUtlis with(String key, Object value) {
        if (router != null && value != null && !TextUtils.isEmpty(key)) {
            if (value instanceof String) {
                router.withString(key, (String) value);
            } else if (value instanceof Integer) {
                router.withInt(key, (Integer) value);
            } else if (value instanceof Boolean) {
                router.withBoolean(key, (Boolean) value);
            } else if (value instanceof Serializable) {
                router.withSerializable(key, (Serializable) value);
            } else if (value instanceof Bundle) {
                router.withBundle(key, (Bundle) value);
            } else if (value instanceof Float) {
                router.withFloat(key, (Float) value);
            } else if (value instanceof Long) {
                router.withLong(key, (Long) value);
            } else if (value instanceof Double) {
                router.withDouble(key, (Double) value);
            } else {
                router.withObject(key, value);
            }
        }
        return this;
    }

    //conflict with with(String key, Object value)
    public ARouterUtlis with(Bundle bundle) {
        if (router != null && bundle != null) {
            router.with(bundle);
        }
        return this;
    }

}
