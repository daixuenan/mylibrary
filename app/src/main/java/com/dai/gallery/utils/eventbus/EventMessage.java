package com.dai.gallery.utils.eventbus;

public class EventMessage {

    private int type;

    private String msg;

    public EventMessage(int type) {
        this.type = type;
    }

    public EventMessage(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
