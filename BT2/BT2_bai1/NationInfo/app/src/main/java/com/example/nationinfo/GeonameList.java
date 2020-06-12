package com.example.nationinfo;

import java.util.ArrayList;

public class GeonameList {
    private ArrayList<Geoname> list = new ArrayList<Geoname>();
    private int length = 0;

    public ArrayList<Geoname>getList(){return this.list;}
    public Geoname get(int index) {return this.list.get(index);}
    public void setList (ArrayList<Geoname> list){this.list = list;}
    public int getLength() {return length;}
    public void setLength(int length) {this.length = length;}

    public void addOneElement(Geoname element){
        this.list.add(element);
        this.length++;
    }
}
