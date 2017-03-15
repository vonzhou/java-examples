package com.vonzhou.learn.commons;

import lombok.Data;

/**
 * @version 2016/12/21.
 */
@Data
public class Part {
    private String atrr1;
    private String atrr2;
    private String prop;

    @Override
    public String toString() {
        return "Part{" + "atrr1='" + atrr1 + '\'' + ", atrr2='" + atrr2 + '\'' + ", prop='" + prop + '\'' + '}';
    }
}
