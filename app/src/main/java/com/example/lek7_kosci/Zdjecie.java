package com.example.lek7_kosci;

public class Zdjecie {
    private int zdjecieId;
    private int wartosc;
    public Zdjecie(int zdjecieId,int wartosc){
        this.zdjecieId = zdjecieId;
        this.wartosc = wartosc;
    }

    public int getZdjecieId() {
        return zdjecieId;
    }

    public int getWartosc() {
        return wartosc;
    }
}
