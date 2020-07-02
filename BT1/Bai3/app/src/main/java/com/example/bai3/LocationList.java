package com.example.bai3;

public class LocationList {
    static private int activeOption=-1;

    static private Location [] locations = {
            new Location("Tower City Center",
                    "https://www.google.com/maps/place/Tower+City+Center/@41.4970314,-81.695495,17z/data=!3m1!4b1!4m5!3m4!1s0x8830f07f1ec04a5b:0x7485f64088433696!8m2!3d41.4970274!4d-81.6933063",
                    "https://www.tripadvisor.com.vn/Attraction_Review-g50207-d145310-Reviews-Tower_City_Center-Cleveland_Ohio.html"),
            new Location("Cleveland Browns",
                    "https://www.google.com/maps/place/S%C3%A2n+v%E1%BA%ADn+%C4%91%E1%BB%99ng+FirstEnergy/@41.5060575,-81.7017368,17z/data=!3m2!4b1!5s0x8830f085d5259d4d:0x3333a5709cce1840!4m5!3m4!1s0x8830f085d448b84d:0xc1d55254b1aa84e9!8m2!3d41.5060535!4d-81.6995481",
                    "https://www.clevelandbrowns.com/"),
            new Location("Cleveland State University",
                    "https://www.google.com/maps/place/2121+Euclid+Ave,+Cleveland,+OH+44115,+Hoa+K%E1%BB%B3/@41.5022787,-81.675804,19z/data=!3m1!4b1!4m5!3m4!1s0x8830fa63f3d2708b:0x3b87b0d713a04fc0!8m2!3d41.5022777!4d-81.6752568",
                    "https://www.csuohio.edu/"),
            new Location("Playhouse Square",
                    "https://www.google.com/maps/place/Playhouse+Square/@41.501097,-81.6833515,17z/data=!4m8!1m2!2m1!1sPlayhouse+Square!3m4!1s0x8830fa7d31bc7e1d:0xd4fedf1ad239019!8m2!3d41.5013167!4d-81.6807944",
                    "http://www.playhousesquare.org/"),
            new Location("The Cleveland Museum of Art",
                    "https://www.google.com/maps/place/The+Cleveland+Museum+of+Art/@41.5090411,-81.614259,17z/data=!4m8!1m2!2m1!1sArt+Museum!3m4!1s0x8830fb8dd37a09d9:0x24667546af3346a!8m2!3d41.5090411!4d-81.6120703",
                    "https://www.clevelandart.org/"),
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