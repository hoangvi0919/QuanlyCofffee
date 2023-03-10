package com.sinhvien.finalproject.QuanLi.Department;

public class Department {
    private int id;
    private String Name;

    public Department() {
    }

    public Department(int id, String name) {
        this.id = id;
        Name = name;
    }

    @Override
    public String toString() {
        return Name ;
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

}