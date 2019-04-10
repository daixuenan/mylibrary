package com.dai.gallery.bean;

import com.dai.mylibrary.bean.FileBean;

import java.io.Serializable;
import java.util.List;

public class HomeListBean implements Serializable {

    private MMTypeBean type;
    private List<FileBean> list;

    public MMTypeBean getType() {
        return type;
    }

    public void setType(MMTypeBean type) {
        this.type = type;
    }

    public List<FileBean> getList() {
        return list;
    }

    public void setList(List<FileBean> list) {
        this.list = list;
    }
}
