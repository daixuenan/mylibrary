package com.dai.gallery.bean;

import java.io.Serializable;

public class MMTypeBean implements Serializable {

    private String description;
    private String id;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
