package com.vonzhou.learn.spring.beanloading;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @version 2016/9/20.
 */
public class TestFactoryBean {
    public static void main(String[] args) {
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("service-context.xml"));
        Car bean = (Car) factory.getBean("car");
        System.out.println(bean);
    }


}
