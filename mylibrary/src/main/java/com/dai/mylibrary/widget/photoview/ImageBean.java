package com.dai.mylibrary.widget.photoview;

import java.io.Serializable;

/**
 * Created by dai on 2017/12/5.
 */

public class ImageBean implements Serializable {

    private String CompressedImgUrl;
    private int ID;
    private String ImgHost;
    private String ImgUrl;
    private String Note;
    private int ReferType;

    public String getCompressedImgUrl() {
        return CompressedImgUrl;
    }

    public void setCompressedImgUrl(String CompressedImgUrl) {
        this.CompressedImgUrl = CompressedImgUrl;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getImgHost() {
        return ImgHost;
    }

    public void setImgHost(String ImgHost) {
        this.ImgHost = ImgHost;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String ImgUrl) {
        this.ImgUrl = ImgUrl;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public int getReferType() {
        return ReferType;
    }

    public void setReferType(int ReferType) {
        this.ReferType = ReferType;
    }

}
