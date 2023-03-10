package com.sinhvien.finalproject.Domain;

import java.io.Serializable;

public class FoodDomain implements Serializable {
    private String mon;
    private String pic;
    private  int gia;
    private int numberInCart;
    public FoodDomain(String mon, String pic, int gia) {
        this.mon = mon;
        this.pic = pic;
        this.gia = gia;
    }

    public FoodDomain(String mon, String pic, int gia, int numberInCart) {
        this.mon = mon;
        this.pic = pic;
        this.gia = gia;
        this.numberInCart=numberInCart;
    }


    public String getMon(){
        return mon;
    }
    public void setMon(String mon){
        this.mon=mon;
    }

    public String getPic(){
        return pic;
    }
    public void setPic(String pic){
        this.pic=pic;
    }

    public int getGia(){ return gia; }
    public void setGia(int gia){
        this.gia= gia;
    }

    public int getNumberInCart() {
        return numberInCart;
    }
    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}


