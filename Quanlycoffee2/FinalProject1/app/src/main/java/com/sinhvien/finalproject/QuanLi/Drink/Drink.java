package com.sinhvien.finalproject.QuanLi.Drink;
import android.graphics.Bitmap;
public class Drink {
    private int id;
    private String Name;
    private String TypeName;
    private Bitmap Image;
    private long money;
    private long point;

    public Drink() {
    }

    public Drink(int id, String name, String typeName, Bitmap image, long money, long point) {
        this.id = id;
        Name = name;
        TypeName = typeName;
        Image = image;
        this.money = money;
        this.point = point;
    }

    @Override
    public String toString() {
        return
                "Name: " + Name +
                        ", TypeName: " + TypeName;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public long getPoint() {
        return point;
    }

    public void setPoint(long point) {
        this.point = point;
    }
}
