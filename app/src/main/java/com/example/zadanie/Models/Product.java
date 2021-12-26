package com.example.zadanie.Models;

import android.os.Parcelable;

public abstract class Product implements Parcelable {

    public String Articul;

    public String getArticul() {
        return Articul;
    }

    public void setArticul(String articul) {
        Articul = articul;
    }

    public String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double Price;

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public Product(String articul, String name, double price){
        Articul = articul;
        Name = name;
        Price = price;
    }
}
