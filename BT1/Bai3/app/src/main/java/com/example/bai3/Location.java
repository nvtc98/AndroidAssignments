package com.example.bai3;

public class Location {
    private String name, geo, url;

    public Location(String name,String geo, String url) {
        this.name = name;
        this.geo = geo;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getGeo() {
        return geo;
    }

    public String getUrl() {
        return url;
    }
}
