package com.example.vechet.knongdai.entity;

import java.io.Serializable;

public class FacebookLoginInfo implements Serializable {
    private int id;
    private String name;
    private String emial;
    private String profileUrl = "abc";

    public FacebookLoginInfo(int id, String name, String emial, String profileUrl) {
        this.id = id;
        this.name = name;
        this.emial = emial;
        this.profileUrl = profileUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmial() {
        return emial;
    }

    public void setEmial(String emial) {
        this.emial = emial;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
