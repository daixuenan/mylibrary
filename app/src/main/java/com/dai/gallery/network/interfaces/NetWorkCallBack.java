package com.dai.gallery.network.interfaces;

import com.alibaba.fastjson.JSON;
import com.dai.gallery.AppApplication;
import com.dai.gallery.network.ApiCode;
import com.dai.gallery.network.base.BaseBean;
import com.dai.mylibrary.utils.ToastUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

/**
 * Created by dai on 2018/11/19.
 */

public abstract class NetWorkCallBack extends AsyncHttpResponseHandler {

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        try {
            String response = new String(responseBody);
            BaseBean baseBean = JSON.parseObject(response, BaseBean.class);
            if (baseBean == null) {
                baseBean = new BaseBean();
            }
            Object data = baseBean.getData();
            if (data == null) {
                data = new Object();
            }
            if (ApiCode.API_SUCCESS == baseBean.getCode()) {
                //请求成功
                this.onResponse(true, baseBean.getCode(), data.toString());
                this.onSucceedMessage(baseBean.getMsg());
            }
//            else if (ApiCode.USER_NOT_LOGIN == baseBean.getCode()) {
//                //token过期
//                AccountUtils.doLogout(AppApplication.getInstance());
//                //FIXME login
////                LoginActivity.start(AppApplication.getInstance(), null);
//            }
            else {
                this.onResponse(false, baseBean.getCode(), baseBean.getMsg());
                ToastUtils.toast(AppApplication.getInstance(), baseBean.getMsg());
            }
// else if (ApiCode.NO_LOGIN.equals(baseBean.getRetCode())) {
//                //token过期
//                Configure.TOKEN = "";
//            }
//            else if (model.getStatus() == ApiCode.UPDATE_TOKEN_NEEDED) {
//                JSONObject jsonData = JSON.parseObject(data.toString());
//                AccountCache.setToken(jsonData.containsKey("auth_token") ? jsonData.getString("auth_token") : "");
//                this.onResponse(true, model.getStatus(), data.toString());
//            } else if (model.getStatus() == ApiCode.MOBILE_ALREADY_EXIST) {
//                this.onResponse(false, model.getStatus(), model.getMessage());
//            } else if (model.getStatus() == ApiCode.TOKEN_EXPIRED) {
//                Intent intent = new Intent(AppApplication.getInstance(), LoginActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                AppApplication.getInstance().startActivity(intent);
//                AccountCache.setToken("");
//            }
//            else{
//                this.onResponse(false, baseBean.getRetCode(), baseBean.getRetMsg());
//            }
        } catch (Exception e) {
//            Toast.makeText(AppApplication.getInstance(), "请求异常", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            this.onResponse(false, ApiCode.API_UNKNOW_EXCEPTION, "服务器繁忙，请重试!");
//            Log.e("onSuccess response:", e.getMessage());
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        try {
            String response = new String(responseBody);
            this.onResponse(false, ApiCode.API_UNKNOW_EXCEPTION, "服务器繁忙，请重试");
//            Log.e("onFailure response:", response);
        } catch (Exception e) {
//            Log.e("onFailure response:", e.getMessage());
            e.printStackTrace();
            this.onResponse(false, ApiCode.API_UNKNOW_EXCEPTION, "服务器繁忙，请重试...");
//            Toast.makeText(AppApplication.getInstance(), "请求失败", Toast.LENGTH_LONG).show();
        }
    }

    //    this.onResponse
    public abstract void onResponse(boolean isSucceed, int status, String response);

    //成功消息
    public void onSucceedMessage(String message) {
    }
}
