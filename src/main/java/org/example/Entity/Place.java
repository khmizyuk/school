package org.example.Entity;

import javax.persistence.Id;

public class Place {
    @Id
    private String  id;

    private String  claster;
    private String  rowLetter;
    private int     number;

    public Place() {}

    public Place(String id, String claster, String rowLetter, int number) {
        this.id = id;
        this.claster = claster;
        this.rowLetter = rowLetter;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClaster() {
        return claster;
    }

    public void setClaster(String claster) {
        this.claster = claster;
    }

    public String getRowLetter() {
        return rowLetter;
    }

    public void setRowLetter(String rowLetter) {
        this.rowLetter = rowLetter;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
