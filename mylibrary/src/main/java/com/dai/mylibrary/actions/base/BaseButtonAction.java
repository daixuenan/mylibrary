package com.dai.mylibrary.actions.base;

import android.content.Context;

public class BaseButtonAction extends BaseAction {

    private String link;

    public BaseButtonAction(Context context, String title, String iconUrl, String link, int key) {
        super(context);
        setTitle(title);
        setIconUrl(iconUrl);
        setKey(key);

        this.link = link;
    }

    @Override
    public void onClick() {
        //TODO: onClick事件
    }
}
