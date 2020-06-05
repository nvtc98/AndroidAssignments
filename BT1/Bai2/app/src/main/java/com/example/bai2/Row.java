package com.example.bai2;

public class Row {
    private String title, url;
    private int image, thump;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public int getImage() {
        return image;
    }

    public int getThump() {
        return thump;
    }

    public Row(String title, int image, int thump, String url){
        this.title=title;
        this.image=image;
        this.thump=thump;
        this.url=url;
    }
}
