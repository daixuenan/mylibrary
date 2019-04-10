package com.dai.mylibrary.widget.photoview.interfaces;

/**
 * Created by yun on 2017/8/15.
 */

public interface OnModifiedListener {

    void onDeleted(String url);

    void onModified(String oldUrl, String newUrl);
}
