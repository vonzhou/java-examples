package com.vonzhou.learn.xstream.annotation;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @version 2016/10/14.
 */
@XStreamAlias("dog")
public class Dog {

//    @XStreamAsAttribute
    private String name;
//    @XStreamAsAttribute
    private String city;

    private String color;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "A " + this.color  + " Dog Named " + this.name + " From " + this.city;
    }
}
