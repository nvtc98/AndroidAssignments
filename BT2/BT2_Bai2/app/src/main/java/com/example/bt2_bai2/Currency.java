package com.example.bt2_bai2;

public class Currency {
    private String title, link, ISO;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getISO() {
        return ISO;
    }

    public void setTitle(String title) {
        this.title = title;
        this.ISO = trim(title);
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Currency() {
    }

    public Currency(String title, String link) {
        this.title = title;
        this.link = link;
        this.ISO = trim(title);
    }

    String trim(String input){
        return input.substring(input.indexOf('(')+1,input.indexOf(')'));
    }
}
