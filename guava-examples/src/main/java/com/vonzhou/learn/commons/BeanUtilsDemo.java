package com.vonzhou.learn.commons;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @version 2016/12/21.
 */
public class BeanUtilsDemo {
    public static void main(String[] args) {
        Full full = new Full();
        full.setAtrr1("attr1");
        full.setAtrr2("attr2");
        full.setAtrr3("attr3");
        full.setAtrr4("attr4");

        Part part = new Part();
        try {
            BeanUtils.copyProperties(part, full);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        part.setProp("prop");

        System.out.println(part);
    }

}
