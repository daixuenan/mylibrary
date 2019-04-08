package com.dai.mylibrary.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by yun on 2017/4/25.
 */

public class MobileUtils {

//    // 判断输入的手机号是否合法
//    public static boolean isMobileNO(String mobiles) {
//        /*
//         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
//		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通） 177
//		 * 阿里通信 177 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
//		 */
//        String telRegex = "[1][3578]\\d{9}";// "[1]"代表第1位为数字1，"[3578]"代表第二位可以为3、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
//        if (mobiles.isEmpty())
//            return false;
//        else
//            return mobiles.matches(telRegex);
//    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public static void diallPhone(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
    public static void callPhone(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(context, "请先打开电话拨通权限", Toast.LENGTH_SHORT).show();
            return;
        }
        context.startActivity(intent);
    }
}
