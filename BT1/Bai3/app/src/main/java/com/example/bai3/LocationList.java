package com.example.bai3;

public class LocationList {
    static private int activeOption=-1;

    static private Location [] locations = {
            new Location("Cleveland Tower City", "https://www.tripadvisor.com.vn/Attraction_Review-g50207-d145310-Reviews-Tower_City_Center-Cleveland_Ohio.html"),
            new Location("Browns Football Field", "https://www.clevelandbrowns.com/"),
            new Location("Cleveland State University", "https://www.csuohio.edu/"),
            new Location("Playhouse Square", "http://www.playhousesquare.org/"),
            new Location("Art Museum", "https://www.clevelandart.org/"),
    };

    public static Location getActiveLocation(){
        return locations[activeOption];
    }

    public static void setActiveOption(int activeOption) {
        LocationList.activeOption = activeOption;
    }

    public static Location[] getLocations() {
        return locations;
    }
}