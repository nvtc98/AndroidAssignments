package com.example.nationinfo;

import java.io.Serializable;

public class Geoname implements Serializable {
    private String continent = "";
    private String capital = "";
    private String languages = "";
    private int geonameId;
    private String south = "";
    private String isoAlpha3 = "";
    private String north = "";
    private String fipsCode = "";
    private String population = "";
    private String east = "";
    private String isoNumeric = "";
    private String areaInSqKm = "";
    private String countryCode = "";
    private String west = "";
    private String countryName = "";
    private String continentName = "";
    private String currencyCode = "";

    public Geoname(String continent, String capital, String languages, int geonameId, String south,
                   String isoAlpha3, String north, String fipsCode, String population, String east,
                   String isoNumeric, String areaInSqKm, String countryCode, String west,
                   String countryName, String continentName, String currencyCode) {
        this.continent = continent;
        this.capital = capital;
        this.languages = languages;
        this.geonameId = geonameId;
        this.south = south;
        this.isoAlpha3 = isoAlpha3;
        this.north = north;
        this.fipsCode = fipsCode;
        this.population = population;
        this.east = east;
        this.isoNumeric = isoNumeric;
        this.areaInSqKm = areaInSqKm;
        this.countryCode = countryCode;
        this.west = west;
        this.countryName = countryName;
        this.continentName = continentName;
        this.currencyCode = currencyCode;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public int getGeonameId() {
        return geonameId;
    }

    public void setGeonameId(int geonameId) {
        this.geonameId = geonameId;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getIsoAlpha3() {
        return isoAlpha3;
    }

    public void setIsoAlpha3(String isoAlpha3) {
        this.isoAlpha3 = isoAlpha3;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getFipsCode() {
        return fipsCode;
    }

    public void setFipsCode(String fipsCode) {
        this.fipsCode = fipsCode;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public String getIsoNumeric() {
        return isoNumeric;
    }

    public void setIsoNumeric(String isoNumeric) {
        this.isoNumeric = isoNumeric;
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

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
