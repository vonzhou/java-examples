package com.vonzhou.learn.section7.domain;

/**
 * @version 2017/3/17.
 */
public class Bar {
    private int year;
    private String loc;

    public Bar(int year, String loc) {
        this.year = year;
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Bar{" + "loc='" + loc + '\'' + ", year=" + year + '}';
    }
}
