package com.example.nationinfo;

import java.util.ArrayList;

public class GEONAME_LIST {
    private ArrayList<GEONAME> list = new ArrayList<GEONAME>();
    private int length = 0;

    public ArrayList<GEONAME>getList(){return this.list;}
    public GEONAME get(int index) {return this.list.get(index);}
    public void setList (ArrayList<GEONAME> list){this.list = list;}
    public int getLength() {return length;}
    public void setLength(int length) {this.length = length;}

    public void addOneElement(GEONAME element){
        this.list.add(element);
        this.length++;
    }
}
