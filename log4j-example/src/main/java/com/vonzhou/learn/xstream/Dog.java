package com.vonzhou.learn.xstream;

/**
 * @version 2016/10/14.
 */
public class Dog {
    private String name;
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "A Dog Named " + this.name + " From " + this.city;
    }
}
