package com.dai.mylibrary.utils;

/**
 * Created by dai on 2018/11/19.
 */

public class DpPxSpUtils {

    private static float scale;

    /**
     *
     * @return screen size
     */
    public static void init(float scale) {
        DpPxSpUtils.scale = scale;
    }

    /**
     * transfor dp to px
     */
    public static int dip2px(float dpValue) {
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * transfor px to dp
     */
    public static int px2dip(float pxValue) {
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * transfor px to sp
     *
     * @return
     */
    public static int px2sp(float pxValue) {
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * transfor sp to px
     *
     * @return
     */
    public static int sp2px(float spValue) {
        return (int) (spValue * scale + 0.5f);
    }
}
