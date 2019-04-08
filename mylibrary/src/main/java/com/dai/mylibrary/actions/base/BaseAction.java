package com.dai.mylibrary.actions.base;

import android.content.Context;

import java.io.Serializable;

public abstract class BaseAction implements Serializable {

    protected Context context;

    //icon
    protected String iconUrl;

    //title
    protected String title;

    //content
    protected String content;

    //hasMessages default false
    private boolean hasMessage = false;

    protected int key;

    public abstract void onClick();

    public BaseAction(Context context) {
        this.context = context;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    protected void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    protected void setContent(String content) {
        this.content = content;
    }

    public boolean isHasMessage() {
        return hasMessage;
    }

    public void setHasMessage(boolean hasMessage) {
        this.hasMessage = hasMessage;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
