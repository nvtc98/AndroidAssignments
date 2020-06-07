package com.example.bt2_bai2;

public class RssFeedModel {
    public String title;
    public String link;
    public String description; //sua lai thanh private

    public RssFeedModel(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;
    }
}
