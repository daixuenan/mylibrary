package com.dai.mylibrary.utils;

import android.text.TextUtils;

/**
 * Created by dai on 2017/11/29.
 */

public class VerifyUtils {

    // 判断输入的手机号是否合法
    public static boolean isMobile(String mobile) {
        String telRegex = "^1\\d{10}$";
        if (mobile.isEmpty())
            return false;
        else
            return mobile.matches(telRegex);
    }

    // 固话验证
    public static boolean isFixedPhone(String phone) {
        String telRegex = "^\\d{3}-\\d{8}$|^\\d{4}-\\d{7}$|^\\d{4}-\\d{8}$";
        if (phone.isEmpty())
            return false;
        else
            return phone.matches(telRegex);
    }

    // 邮箱验证
    public static boolean isEmail(String email) {
        String telRegex = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";
        if (email.isEmpty())
            return false;
        else
            return email.matches(telRegex);
    }

    // 身份证验证
    public static boolean isIDCard(String card) {
        if (TextUtils.isEmpty(card)) {
            return false;
        }
        String telRegex = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
        if (card.isEmpty())
            return false;
        else
            return card.matches(telRegex);
    }

//    RxPermissions rxPermissions = new RxPermissions(this);
//                    rxPermissions.requestEach(Manifest.permission.CALL_PHONE).subscribe(new Consumer<Permission>() {
//        @Override
//        public void accept(Permission permission) throws Exception {
//            if (permission.granted) {
//                //用户已经同意该权限
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel));
//                if (ActivityCompat.checkSelfPermission(MBrowserview.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return;
//                }
//                startActivity(intent);
//            } else if (permission.shouldShowRequestPermissionRationale) {
//                //用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
//            } else {
//                //用户拒绝了该权限，并且选中『不再询问』，提醒用户手动打开权限
//            }
//        }
//    });
}
