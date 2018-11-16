package com.example.vechet.knongdai.entity;

import java.io.Serializable;

public class SubCateId implements Serializable {



    private int subCateId;
    private String subCateTitle;

    public SubCateId(){}

    public SubCateId(int subCateId, String subCateTitle) {
        this.subCateId = subCateId;
        this.subCateTitle = subCateTitle;
    }

    public int getSubCateId() {
        return subCateId;
    }

    public void setSubCateId(int subCateId) {
        this.subCateId = subCateId;
    }

    public String getSubCateTitle() {
        return subCateTitle;
    }

    public void setSubCateTitle(String subCateTitle) {
        this.subCateTitle = subCateTitle;
    }
}
