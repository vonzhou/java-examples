package com.vonzhou.learn.springaspectj;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 注意Spring默认是使用Interface来创建代理的
 * @see 'http://spring.io/blog/2007/07/19/debunking-myths-proxies-impact-performance/'
 * @author vonzhou
 */
public class Main2 {
    public static void main(String[] args) {
        //不要忘了导入 spring-core 包，因为需要里面的运行时异常 BeansException
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");

        Thinker volunteer = (Thinker) context.getBean("volunteer");
        volunteer.thinkOfSomething("我在思考自己的人生..");

    }
}
