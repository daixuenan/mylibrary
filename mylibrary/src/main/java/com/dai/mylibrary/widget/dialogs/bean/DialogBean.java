package com.dai.mylibrary.widget.dialogs.bean;

import java.io.Serializable;

public class DialogBean implements Serializable {

    //标题
    private String title;

    //内容
    private String content;

    //确认按钮文案
    private String btnTextOk;

    //取消按钮文案
    private String btnTextCancel;

    //对话框处理类型
    private int type;

    //链接
    private String url;

    public DialogBean() {
    }

    public DialogBean(String content, String btnTextOk, int type) {
        this.content = content;
        this.btnTextOk = btnTextOk;
        this.type = type;
    }

    public DialogBean(String content, String url, String btnTextOk, int type) {
        this.content = content;
        this.url = url;
        this.btnTextOk = btnTextOk;
        this.type = type;
    }

    /**
     * Extend
     * 扩展字段
     */

    //电话号码字符串(展示用)
    private String phoneText;

    //电话号码
    private String phone;

    //天数
    private String days;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoneText() {
        return phoneText;
    }

    public void setPhoneText(String phoneText) {
        this.phoneText = phoneText;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBtnTextOk() {
        return btnTextOk;
    }

    public void setBtnTextOk(String btnTextOk) {
        this.btnTextOk = btnTextOk;
    }

    public String getBtnTextCancel() {
        return btnTextCancel;
    }

    public void setBtnTextCancel(String btnTextCancel) {
        this.btnTextCancel = btnTextCancel;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
