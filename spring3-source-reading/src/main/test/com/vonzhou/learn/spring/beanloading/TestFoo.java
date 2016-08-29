package com.vonzhou.learn.spring.beanloading;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @version 2016/8/29.
 */
public class TestFoo {

    @Test
    public void testExecute(){
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("service-context.xml"));
        Foo bean = (Foo) factory.getBean("foo");
        bean.execute();
    }
}
