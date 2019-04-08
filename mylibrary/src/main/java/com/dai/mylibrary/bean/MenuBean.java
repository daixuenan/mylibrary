package com.dai.mylibrary.bean;

import java.io.Serializable;

public class MenuBean implements Serializable {

    //菜单图标url
    private String iconUrl;

    //菜单文本标签
    private String label;

    //跳转H5地址
    private String url;

    //原生页面code
    private String code;

    //扩展参数
    private Object data;

    public MenuBean() {
    }

    public MenuBean(String iconUrl, String label, String url, Object data) {
        this.iconUrl = iconUrl;
        this.label = label;
        this.url = url;
        this.data = data;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
