package com.dai.mylibrary.actions.base;

import android.content.Context;

public class BaseLabelAction extends BaseAction {

    private String link;
    private int key;

    public BaseLabelAction(Context context, String title, String iconUrl, String text, String link, int key) {
        super(context);
        setTitle(title);
        setIconUrl(iconUrl);
        setContent(text);

        this.link = link;
        this.key = key;
    }

    @Override
    public void onClick() {
        //TODO: onClick事件
    }
}
