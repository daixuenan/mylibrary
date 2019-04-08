package com.dai.gallery.network;

/**
 * Created by dai on 2018/11/19.
 */

public class ApiPutRequest {

//    public static void putModifyUserInfo(String ChineseName, String Email, String ProvinceID, String CityID, String AreaID, String Address, String BankName, String BankCard, String IDCard, String Mobile, String MsgToken, NetWorkCallBack netWorkCallBack) {
//        try {
//            JSONObject data = new JSONObject();
//            if (!TextUtils.isEmpty(ChineseName)) {
//                data.put("ChineseName", ChineseName);
//            }
//            if (!TextUtils.isEmpty(Email)) {
//                data.put("Email", Email);
//            }
//            if (!TextUtils.isEmpty(ProvinceID)) {
//                data.put("ProvinceID", ProvinceID);
//            }
//            if (!TextUtils.isEmpty(CityID)) {
//                data.put("CityID", CityID);
//            }
//            if (!TextUtils.isEmpty(AreaID)) {
//                data.put("AreaID", AreaID);
//            }
//            if (!TextUtils.isEmpty(Address)) {
//                data.put("Address", Address);
//            }
//            if (!TextUtils.isEmpty(BankName)) {
//                data.put("BankName", BankName);
//            }
//            if (!TextUtils.isEmpty(BankCard)) {
//                data.put("BankCard", BankCard);
//            }
//            if (!TextUtils.isEmpty(IDCard)) {
//                data.put("IDCard", IDCard);
//            }
//            if (!TextUtils.isEmpty(Mobile)) {
//                data.put("Mobile", Mobile);
//            }
//            if (!TextUtils.isEmpty(MsgToken)) {
//                data.put("MsgToken", MsgToken);
//            }
//
//            StringEntity entity = new StringEntity(data.toString(), "UTF-8");
//            AsyncApiClient.put(entity, Urls.API_PUT_USER_INFO, netWorkCallBack);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void putSSID(String SSID, String Type, String Name, String Password, String Mode, String ReferStoreID, NetWorkCallBack netWorkCallBack) {
//        try {
//            JSONObject data = new JSONObject();
//            if (!TextUtils.isEmpty(Type)) {
//                data.put("Type", Type);
//            }
//            if (!TextUtils.isEmpty(Name)) {
//                data.put("Name", Name);
//            }
//            if (!TextUtils.isEmpty(Password)) {
//                data.put("Password", Password);
//            }
//            if (!TextUtils.isEmpty(Mode)) {
//                data.put("Mode", Mode);
//            }
//            if (!TextUtils.isEmpty(ReferStoreID)) {
//                data.put("ReferStoreID", ReferStoreID);
//            }
//            String url = String.format("%s/%s", Urls.API_GET_CLIENT_EDIT_SSID, SSID);
//            StringEntity entity = new StringEntity(data.toString(), "UTF-8");
//            AsyncApiClient.put(entity, url, netWorkCallBack);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void putOrganizationUserInfo(String UserID, String account, String title, String department, String profile1, String profile2, String role, String perms, String password, String mobilephone, NetWorkCallBack netWorkCallBack) {
//        try {
//            JSONObject data = new JSONObject();
//            if (account != null) {
//                data.put("account", account);
//            }
//            if (title != null) {
//                data.put("title", title);
//            }
//            if (department != null) {
//                data.put("department", department);
//            }
//            if (profile1 != null) {
//                data.put("profile1", profile1);
//            }
//            if (profile2 != null) {
//                data.put("profile2", profile2);
//            }
//            if (role != null) {
//                data.put("role", role);
//            }
//            if (perms != null) {
//                data.put("perms", perms);
//            }
//            if (password != null) {
//                data.put("password", password);
//            }
//            if (mobilephone != null) {
//                data.put("mobilephone", mobilephone);
//            }
//            StringEntity entity = new StringEntity(data.toString(), "UTF-8");
//            String url = Urls.API_PUT_ORGANIZATION_USER_INFO + UserID;
//            AsyncApiClient.put(entity, url, netWorkCallBack);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void putUserInfo(int userID, JSONObject data, NetWorkCallBack netWorkCallBack) {
//        try {
//            StringEntity entity = new StringEntity(data.toString(), "UTF-8");
//            String url = Urls.API_PUT_ORGANIZATION_USER_INFO;
//            if (userID != 0) {
//                url += userID + "";
//            }
//            AsyncApiClient.put(entity, url, netWorkCallBack);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void putDepartmentPost(int DeptID, int IsUnitStaff, List<PostEntity> PostInfos, NetWorkCallBack netWorkCallBack) {
//        try {
////            是否部门编制(0.否, 1.是)[EnumType: 1001]
//            JSONObject data = new JSONObject();
//            JSONArray postArray = new JSONArray();
//            data.put("IsUnitStaff", IsUnitStaff);
//            for (PostEntity postEntity : PostInfos) {
//                JSONObject obj = new JSONObject();
//                String userId = null;
//                obj.put("PostID", postEntity.getPostID());
//                if (postEntity.getUserID() != null) {
//                    if (postEntity.getUserID().getUserID() != 0) {
//                        userId = postEntity.getUserID().getUserID() + "";
//                    }
//                }
//                obj.put("UserID", userId);
//                postArray.add(obj);
//            }
//            data.put("PostInfos", postArray);
//            String url = String.format(Urls.API_PUT_ORGANIZATION_DEPARTMENT_POST, DeptID);
//            StringEntity entity = new StringEntity(JSON.toJSONString(data, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty), "UTF-8");
//            AsyncApiClient.put(entity, url, netWorkCallBack);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    //    人事管理-部门 - 修改部门岗位
//    public static void putDepartmentPost(int DeptPostID, int UserID, NetWorkCallBack netWorkCallBack) {
//        try {
//            JSONObject data = new JSONObject();
//            data.put("UserID", UserID);
//            String url = Urls.API_PUT_ORGANIZATION_POST + DeptPostID;
//            StringEntity entity = new StringEntity(JSON.toJSONString(data, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty), "UTF-8");
//            AsyncApiClient.put(entity, url, netWorkCallBack);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    //    附录-云配置 - 修改用户云配置
//    public static void putGlobalsCloudConf(JSONArray AppMenu, NetWorkCallBack netWorkCallBack) {
//        try {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("AppMenu", AppMenu);
//            String url = Urls.API_PUT_GLOBALS_CLOUD_CONF;
//            StringEntity entity = new StringEntity(jsonObject.toString(), "UTF-8");
//            AsyncApiClient.put(entity, url, netWorkCallBack);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
