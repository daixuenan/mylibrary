package com.dai.mylibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import java.io.File;
import java.math.BigDecimal;

public class CacheUtils {

    private final static String FOLDER = "YPY_DATA";
    private final static String FOLDER_USER = "USER_DATA";

    public final static String FIRST_ENTER = "is_first_enter";
    public final static String TOKEN = "token";
    public final static String LAST_UPDATE_DATE = "last_update_date";
    public final static String USER_INFO = "user_info";
    public final static String DEVICE_TOKEN = "device_token";

    //当前版本是否显示引导页 根据版本号区分
    public final static String SHOW_GUIDE = "show_guide";

    public static void setString(Context context, String key, String str) {
        SharedPreferences sp = context.getSharedPreferences(FOLDER, Context.MODE_PRIVATE);
        SharedPreferences.Editor et = sp.edit();
        et.putString(key, str);
        et.commit();
    }

    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FOLDER, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    public static boolean getBool(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FOLDER, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    public static void setBool(Context context, String key, boolean bool) {
        SharedPreferences sp = context.getSharedPreferences(FOLDER, Context.MODE_PRIVATE);
        SharedPreferences.Editor et = sp.edit();
        et.putBoolean(key, bool);
        et.commit();
    }

    public static void setUserString(Context context, String key, String str) {
        SharedPreferences sp = context.getSharedPreferences(FOLDER_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor et = sp.edit();
        et.putString(key, str);
        et.commit();
    }

    public static String getUserString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FOLDER_USER, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    public static void clearUserCache(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FOLDER_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor et = sp.edit();
        et.clear();
        et.commit();
    }

    public static boolean clearFileCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
        return true;
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static String caculateFileCache(Context context) {
        try {
            long cacheSize = getFolderSize(context.getCacheDir());
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                cacheSize += getFolderSize(context.getExternalCacheDir());
            }
            return getFormatSize(cacheSize);
        } catch (Exception e) {
        }
        return "0M";
    }

    // 获取文件大小
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
//            return size + "Byte";
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "K";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "M";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }
}
