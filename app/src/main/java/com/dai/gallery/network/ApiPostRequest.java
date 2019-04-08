package com.dai.gallery.network;

import android.os.Build;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dai.gallery.AppApplication;
import com.dai.gallery.network.base.AsyncApiClient;
import com.dai.gallery.network.interfaces.NetWorkCallBack;
import com.dai.gallery.utils.AccountUtils;
import com.dai.gallery.utils.Configure;
import com.dai.mylibrary.bean.UserBean;
import com.dai.mylibrary.utils.app.DeviceUtils;
import com.loopj.android.http.RequestParams;

import org.apache.http.entity.StringEntity;

import java.io.File;

/**
 * Created by dai on 2018/11/19.
 */

public class ApiPostRequest {

    //发送短信验证码
    //业务类型  1:手机注册,2:登录,3:短信重置密码,4:绑定手机,5:短信重置提佣密码,6:绑定银行卡

    public static final int TYPE_REGISTER = 1;
    public static final int TYPE_LOGIN = 2;
    public static final int TYPE_RESET_PASSWORD = 3;
    public static final int TYPE_BIND_PHONE = 4;
    public static final int TYPE_RESET_WITHDRAW_PASSWORD = 5;
    public static final int TYPE_BIND_BANKCARD = 6;

    public static void sendSmsCode(int bizType, String phone, NetWorkCallBack netWorkCallBack) {
        try {
            JSONObject requestObject = new JSONObject();

            requestObject.put("bizType", bizType);
            requestObject.put("phone", phone);

            StringEntity entity = new StringEntity(requestObject.toString(), "UTF-8");
            AsyncApiClient.post(entity, Apis.API_POST_MESSAGE_SEND_SMS_CODE, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loginSmsCode(String phone, String smsVerifyCode, NetWorkCallBack netWorkCallBack) {
        try {
            JSONObject requestObject = new JSONObject();

            requestObject.put("phone", phone);
            requestObject.put("smsVerifyCode", smsVerifyCode);

            StringEntity entity = new StringEntity(requestObject.toString(), "UTF-8");
            AsyncApiClient.post(entity, Apis.API_POST_USER_LOGIN_SMS_CODE, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void userUpdateData(UserBean userBean, NetWorkCallBack netWorkCallBack) {
        try {
            JSONObject requestObject = new JSONObject();

            requestObject.put("realName", userBean.getRealName());
            requestObject.put("identity", userBean.getIdentity());

            StringEntity entity = new StringEntity(requestObject.toString(), "UTF-8");
            AsyncApiClient.post(entity, Apis.API_POST_USER_UPDATE_DATA, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewRecode(String channelName, String subChannel, String productCode, NetWorkCallBack netWorkCallBack) {
        try {
            JSONObject requestObject = new JSONObject();

            requestObject.put("channelName", channelName);
            requestObject.put("subChannel", subChannel);
            requestObject.put("productCode", productCode);
            requestObject.put("uuid", Configure.DEVICE_TOKEN);

            StringEntity entity = new StringEntity(requestObject.toString(), "UTF-8");
            AsyncApiClient.post(entity, Apis.API_POST_PRODUCT_VIEW_RECODE, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void countNetwork(NetWorkCallBack netWorkCallBack) {
        try {
            JSONObject requestObject = new JSONObject();

            String equipment = Build.MODEL;
            String sysVersion = Build.VERSION.RELEASE;
            String system = "Android";
            String uuid = Configure.DEVICE_TOKEN;
            if (TextUtils.isEmpty(uuid)) {
                return;
            }

            String realName = "";
            String userCode = "";

            if (!TextUtils.isEmpty(Configure.TOKEN)) {
                UserBean userBean = AccountUtils.getUserInfo();
                realName = userBean.getRealName();
                userCode = userBean.getCode();
            }

            requestObject.put("equipment", equipment);
            if (!TextUtils.isEmpty(realName)) {
                requestObject.put("realName", realName);
            }
            requestObject.put("sysVersion", sysVersion);
            requestObject.put("system", system);
            if (!TextUtils.isEmpty(userCode)) {
                requestObject.put("userCode", userCode);
            }
            requestObject.put("uuid", uuid);

            StringEntity entity = new StringEntity(requestObject.toString(), "UTF-8");
            AsyncApiClient.post(entity, Apis.API_POST_NETWORK_COUNT, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * old
     *
     * @param phone
     * @param password
     * @param netWorkCallBack
     */

    //登录
    public static void loginPhonePassword(String phone, String password, NetWorkCallBack netWorkCallBack) {
        try {
            JSONObject requestObject = new JSONObject();

            requestObject.put("phone", phone);
            requestObject.put("password", password);

            StringEntity entity = new StringEntity(JSON.toJSONString(requestObject), "UTF-8");
            AsyncApiClient.post(entity, Apis.API_POST_LOGIN_PHONE_PASSWORD, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //校验短信验证码
    public static void checkValidCode(int bizType, String phone, String smsVerifyCode, NetWorkCallBack netWorkCallBack) {
        try {
            JSONObject requestObject = new JSONObject();

            requestObject.put("bizType", bizType);
            requestObject.put("phone", phone);
            requestObject.put("smsVerifyCode", smsVerifyCode);

            StringEntity entity = new StringEntity(requestObject.toString(), "UTF-8");
            AsyncApiClient.post(entity, Apis.API_POST_MESSAGE_CHECK, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //手机号注册
    public static void userRegister(String phone, String password, String smsVerifyCode, NetWorkCallBack netWorkCallBack) {
        try {
            JSONObject requestObject = new JSONObject();

            requestObject.put("phone", phone);
            requestObject.put("password", password);
            requestObject.put("smsVerifyCode", smsVerifyCode);

            StringEntity entity = new StringEntity(requestObject.toString(), "UTF-8");
            AsyncApiClient.post(entity, Apis.API_POST_USER_REGISTER, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //重置密码
    public static void resetPassword(String phone, String newPassword, String smsVerifyCode, NetWorkCallBack netWorkCallBack) {
        try {
            JSONObject requestObject = new JSONObject();

            requestObject.put("phone", phone);
            requestObject.put("newPassword", newPassword);
            requestObject.put("smsVerifyCode", smsVerifyCode);

            StringEntity entity = new StringEntity(requestObject.toString(), "UTF-8");
            AsyncApiClient.post(entity, Apis.API_POST_USER_RESET_PASSWORD, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //上传图片
    public static void uploadFile(String filePath, NetWorkCallBack netWorkCallBack) {
        try {
            RequestParams params = new RequestParams();
            params.put("file", new File(filePath));
            AsyncApiClient.post(Apis.API_POST_FILE_HEADIMAGE, params, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修改用户昵称
    public static void updateNickName(String nickname, NetWorkCallBack netWorkCallBack) {
        try {
            JSONObject requestObject = new JSONObject();

            requestObject.put("nickname", nickname);

            StringEntity entity = new StringEntity(requestObject.toString(), "UTF-8");
            AsyncApiClient.post(entity, Apis.API_POST_USER_UPDATE_NICKNAME, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //设置提佣密码
    public static void updateWithdrawPassword(String payPassword, NetWorkCallBack netWorkCallBack) {
        try {
            JSONObject requestObject = new JSONObject();

            requestObject.put("payPassword", payPassword);

            StringEntity entity = new StringEntity(requestObject.toString(), "UTF-8");
            AsyncApiClient.post(entity, Apis.API_POST_USER_COMISSION_PASSWORD, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修改登录密码
    public static void updateLoginPassword(String oldPassword, String newPassword, NetWorkCallBack netWorkCallBack) {
        try {
            JSONObject requestObject = new JSONObject();

            requestObject.put("oldPassword", oldPassword);
            requestObject.put("newPassword", newPassword);

            StringEntity entity = new StringEntity(requestObject.toString(), "UTF-8");
            AsyncApiClient.post(entity, Apis.API_POST_USER_LOGIN_PASSWORD, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //新增绑卡信息
    public static void addBankCard(String bankCode, String cardNumber, String phone, String userCode, NetWorkCallBack netWorkCallBack) {
        try {
            JSONObject requestObject = new JSONObject();

            requestObject.put("bankCode", bankCode);
            requestObject.put("cardNumber", cardNumber);
            requestObject.put("phone", phone);
            requestObject.put("userCode", userCode);

            StringEntity entity = new StringEntity(requestObject.toString(), "UTF-8");
            AsyncApiClient.post(entity, Apis.API_POST_BANK_SAVE, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //

    /**
     * old
     *
     * @param params
     */
    private static void addHeaders(RequestParams params) {
        if (!TextUtils.isEmpty(Configure.TOKEN)) {
            params.put("token", Configure.TOKEN);
        }
        // 1 android 2 iOS
        params.put("type", "1");
        params.put("equipment", Build.MODEL);
        params.put("deviceToken", DeviceUtils.getDeviceToken(AppApplication.getInstance()));
        params.put("appVersion", Configure.APP_VERSION);
    }

//    //兑换红包
//    public static void postUserRedeemCode(String code, NetWorkCallBack netWorkCallBack) {
//        RequestParams params = new RequestParams();
//        addHeaders(params);
//
//        params.put("code", code);
//
//        AsyncApiClient.post(Apis.API_POST_USER_REDEEM_CODE, params, netWorkCallBack);
//    }
//
//    //兑换红包
//    public static void postMessageUpdate(int id, NetWorkCallBack netWorkCallBack) {
//        RequestParams params = new RequestParams();
//        addHeaders(params);
//
//        params.put("id", id);
//        params.put("status", 1);
//
//        AsyncApiClient.post(Apis.API_POST_MESSAGE_UPDATE, params, netWorkCallBack);
//    }
//
//    //直投—确定支付
//    public static void postPayInvestDirect(double amount, String projectId, int hongbaoId, int couponId, NetWorkCallBack netWorkCallBack) {
//        RequestParams params = new RequestParams();
//        addHeaders(params);
//
//        params.put("amount", amount);
//        params.put("projectId", projectId);
//        if (hongbaoId != 0) {
//            params.put("hongbaoId", hongbaoId);
//        }
//        if (couponId != 0) {
//            params.put("couponId", couponId);
//        }
//
//        AsyncApiClient.post(Apis.API_POST_PAY_INVEST_DIRECT, params, netWorkCallBack);
//    }
//
//    //智投支付
//    public static void postPayInvestAuto(double amount, int packageId, int hbId, int rateCoupon, NetWorkCallBack netWorkCallBack) {
//        RequestParams params = new RequestParams();
//        addHeaders(params);
//
//        params.put("amount", amount);
//        params.put("packageId", packageId);
//        if (hbId != 0) {
//            params.put("hbId", hbId);
//        }
//        if (rateCoupon != 0) {
//            params.put("rateCoupon", rateCoupon);
//        }
//
//        AsyncApiClient.post(Apis.API_POST_PAY_INVEST_AUTO, params, netWorkCallBack);
//    }
}
