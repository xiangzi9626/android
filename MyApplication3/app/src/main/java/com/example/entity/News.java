package com.example.entity;

import android.net.Uri;

public class News {

    private Integer id;
    private  String title;
    private String img;
    private String url;

    public News(Integer id, String title, String img, String url) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
