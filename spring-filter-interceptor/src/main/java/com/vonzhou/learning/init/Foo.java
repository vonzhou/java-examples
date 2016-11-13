package com.vonzhou.learning.init;

import org.springframework.beans.factory.InitializingBean;

/**
 * @version 2016/11/13.
 */
public class Foo implements InitializingBean {
    public void afterPropertiesSet() throws Exception {
        System.out.println("Foo init(afterPropertiesSet)..." + this);
    }

    public void init() {
        System.out.println("Foo init..." + this);
    }
}
