package com.vonzhou.learn.main;

import com.vonzhou.learn.domain.Bar;
import com.vonzhou.learn.domain.Dog;
import com.vonzhou.learn.domain.Foo;
import com.vonzhou.learn.service.DogService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @version 2017/3/17.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"service-context.xml"});
        Dog dog = (Dog) context.getBean("dog2");
        System.out.println(dog.getName());

        DogService dogService = (DogService) context.getBean("dogService");

        Foo foo = (Foo) context.getBean("foo");

        Bar bar = (Bar) context.getBean("bar2");
        System.out.println(bar);

    }
}
