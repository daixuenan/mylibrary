package com.dai.gallery.bean;

import java.io.Serializable;
import java.util.List;

public class HomeListBean implements Serializable {

    private MMTypeBean type;
    private List<MMBean> list;

    public MMTypeBean getType() {
        return type;
    }

    public void setType(MMTypeBean type) {
        this.type = type;
    }

    public List<MMBean> getList() {
        return list;
    }

    public void setList(List<MMBean> list) {
        this.list = list;
    }
}
