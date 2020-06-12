package com.example.nationinfo;

import java.io.Serializable;

public class Geoname implements Serializable {
    private int geonameId;
    private String population = "";
    private String areaInSqKm = "";
    private String countryCode = "";
    private String countryName = "";

    public Geoname(int geonameId, String population, String areaInSqKm,
                   String countryCode, String countryName) {
        this.geonameId = geonameId;
        this.population = population;
        this.areaInSqKm = areaInSqKm;
        this.countryCode = countryCode;
        this.countryName = countryName;
    }

    public int getGeonameId() {
        return geonameId;
    }

    public void setGeonameId(int geonameId) {
        this.geonameId = geonameId;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }
    public String getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(String areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
