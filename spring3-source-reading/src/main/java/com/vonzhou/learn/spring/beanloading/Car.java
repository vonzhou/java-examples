package com.vonzhou.learn.spring.beanloading;

/**
 * @version 2016/9/20.
 */
public class Car {
    private int maxSpeed;
    private String brand;
    private Double price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.brand + ":" + this.maxSpeed + ":" + this.price;
    }
}
