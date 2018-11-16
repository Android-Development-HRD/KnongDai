package com.example.vechet.knongdai.entity;

import java.io.Serializable;

public class QuerySearchId implements Serializable {
    private int querySearchId;
    private String querySerachLink;
    private String querySearchTitle;

    public QuerySearchId(){}

    public QuerySearchId(int querySearchId, String querySerachLink, String querySearchTitle) {
        this.querySearchId = querySearchId;
        this.querySerachLink = querySerachLink;
        this.querySearchTitle = querySearchTitle;
    }

    public int getQuerySearchId() {
        return querySearchId;
    }

    public void setQuerySearchId(int querySearchId) {
        this.querySearchId = querySearchId;
    }

    public String getQuerySerachLink() {
        return querySerachLink;
    }

    public void setQuerySerachLink(String querySerachLink) {
        this.querySerachLink = querySerachLink;
    }

    public String getQuerySearchTitle() {
        return querySearchTitle;
    }

    public void setQuerySearchTitle(String querySearchTitle) {
        this.querySearchTitle = querySearchTitle;
    }
}
