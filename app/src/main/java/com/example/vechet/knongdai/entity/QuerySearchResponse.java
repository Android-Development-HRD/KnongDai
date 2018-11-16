package com.example.vechet.knongdai.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuerySearchResponse {
    @Expose
    @SerializedName("pagination")
    private QuerySearchResponse.PaginationEntity pagination;
    @Expose
    @SerializedName("data")
    private List<QuerySearchResponse.DataEntity> data;
    @Expose
    @SerializedName("msg")
    private String msg;
    @Expose
    @SerializedName("status")
    private boolean status;
    @Expose
    @SerializedName("code")
    private String code;

    public QuerySearchResponse.PaginationEntity getPagination() {
        return pagination;
    }

    public void setPagination(QuerySearchResponse.PaginationEntity pagination) {
        this.pagination = pagination;
    }

    public List<QuerySearchResponse.DataEntity> getData() {
        return data;
    }

    public void setData(List<QuerySearchResponse.DataEntity> data) {
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
        @SerializedName("pic_url")
        private String picUrl;
        @Expose
        @SerializedName("des")
        private String des;
        @Expose
        @SerializedName("type")
        private String type;
        @Expose
        @SerializedName("title")
        private String title;
        @Expose
        @SerializedName("email")
        private String email;
        @Expose
        @SerializedName("phone")
        private String phone;
        @Expose
        @SerializedName("address")
        private String address;
        @Expose
        @SerializedName("link")
        private String link;
        @Expose
        @SerializedName("id")
        private int id;

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public class PaginationEntity {
        @Expose
        @SerializedName("total_page")
        private int totalPage;
        @Expose
        @SerializedName("total_record")
        private int totalRecord;
        @Expose
        @SerializedName("current_page")
        private int currentPage;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalRecord() {
            return totalRecord;
        }

        public void setTotalRecord(int totalRecord) {
            this.totalRecord = totalRecord;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }
    }

}
