package sample;

import java.io.Serializable;

public class Shop implements Serializable {

    private final String name;
    private int count;
    private double price;
    private double perSec;

    public Shop(String name, int count, double price, double perSec) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.perSec = perSec;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getPerSec() {
        return perSec;
    }
    public String getName() {
        return name;
    }
    public void updateFields(Shop newShop) {
        this.count = newShop.count;
        this.perSec = newShop.perSec;
        this.price = newShop.price;
    }

}
