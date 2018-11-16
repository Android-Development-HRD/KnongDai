package com.example.vechet.knongdai.entity;

import java.io.Serializable;

public class MainCateId implements Serializable {
    private int mainId;
    private String mainTitle;

    public MainCateId(int mainId, String mainTitle) {
        this.mainId = mainId;
        this.mainTitle = mainTitle;
    }

    public int getMainId() {
        return mainId;
    }

    public void setMainId(int mainId) {
        this.mainId = mainId;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }
}
