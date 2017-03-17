package com.vonzhou.learn.domain;

/**
 * 测试循环依赖
 * BeanCurrentlyInCreationException
 * 
 * @version 2017/3/17.
 */
public class CircularA {
    private CircularB b;

    public CircularA(CircularB b) {
        this.b = b;
    }
}
