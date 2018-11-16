package com.example.vechet.knongdai.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubByMainIdResponse {

    @Expose
    @SerializedName("data")
    private List<DataEntity> data;
    @Expose
    @SerializedName("msg")
    private String msg;
    @Expose
    @SerializedName("status")
    private boolean status;
    @Expose
    @SerializedName("code")
    private String code;

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public class DataEntity {
        @Expose
        @SerializedName("total_url")
        private int totalUrl;
        @Expose
        @SerializedName("des")
        private String des;
        @Expose
        @SerializedName("cate_name")
        private String cateName;
        @Expose
        @SerializedName("status")
        private boolean status;
        @Expose
        @SerializedName("id")
        private int id;

        public int getTotalUrl() {
            return totalUrl;
        }

        public void setTotalUrl(int totalUrl) {
            this.totalUrl = totalUrl;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getCateName() {
            return cateName;
        }

        public void setCateName(String cateName) {
            this.cateName = cateName;
        }

        public boolean getStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
