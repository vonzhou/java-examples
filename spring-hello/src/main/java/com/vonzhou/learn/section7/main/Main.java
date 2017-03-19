package com.vonzhou.learn.section7.main;

import com.vonzhou.learn.section7.domain.Bar;
import com.vonzhou.learn.section7.domain.Dog;
import com.vonzhou.learn.section7.domain.Foo;
import com.vonzhou.learn.section7.service.DogService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @version 2017/3/17.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"section7/service-context.xml"});
        Dog dog = (Dog) context.getBean("dog2");
        System.out.println(dog.getName());

        DogService dogService = (DogService) context.getBean("dogService");

        Foo foo = (Foo) context.getBean("foo");

        Bar bar = (Bar) context.getBean("bar2");
        System.out.println(bar);

    }
}
