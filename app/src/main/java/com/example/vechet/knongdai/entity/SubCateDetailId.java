package com.example.vechet.knongdai.entity;

import java.io.Serializable;

public class SubCateDetailId implements Serializable {
    private int subCateDetailid;
    private String subCateDetailLink;
    private String subCateDetailTitle;

    public SubCateDetailId(int subCateDetailid, String subCateDetailLink, String subCateDetailTitle) {
        this.subCateDetailid = subCateDetailid;
        this.subCateDetailLink = subCateDetailLink;
        this.subCateDetailTitle = subCateDetailTitle;
    }

    public int getSubCateDetailid() {
        return subCateDetailid;
    }

    public void setSubCateDetailid(int subCateDetailid) {
        this.subCateDetailid = subCateDetailid;
    }

    public String getSubCateDetailLink() {
        return subCateDetailLink;
    }

    public void setSubCateDetailLink(String subCateDetailLink) {
        this.subCateDetailLink = subCateDetailLink;
    }

    public String getSubCateDetailTitle() {
        return subCateDetailTitle;
    }

    public void setSubCateDetailTitle(String subCateDetailTitle) {
        this.subCateDetailTitle = subCateDetailTitle;
    }
}
