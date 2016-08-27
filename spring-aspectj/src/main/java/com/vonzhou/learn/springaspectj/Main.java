package com.vonzhou.learn.springaspectj;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author vonzhou
 */
public class Main {
    public static void main(String[] args) {
        //不要忘了导入 spring-core 包，因为需要里面的运行时异常 BeansException
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");

        Performer performer = (Performer) context.getBean("guitarPerformer");
        try {
            performer.perform();
        } catch (PerformanceException e) {
            e.printStackTrace();
        }

    }
}
