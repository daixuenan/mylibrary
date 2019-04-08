package com.dai.mylibrary.bean;

import java.io.Serializable;

public class LabelBean implements Serializable {

    private String label;

    private String content;

    public LabelBean(String label, String content) {
        this.label = label;
        this.content = content;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
