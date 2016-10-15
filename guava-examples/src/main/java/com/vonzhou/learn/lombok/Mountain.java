package com.vonzhou.learn.lombok;

import lombok.Data;

/**
 * 学习 Lombok 避免冗余代码
 * http://jnb.ociweb.com/jnb/jnbJan2010.html
 * @version 2016/10/9.
 */
@Data
public class Mountain {
    private String name;
    private double latitude, longitude;
    private String country;
}
