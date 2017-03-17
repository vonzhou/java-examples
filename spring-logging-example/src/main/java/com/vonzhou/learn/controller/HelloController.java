package com.vonzhou.learn.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统属性会覆盖 conf.properties 文件中定义的key/value
 * 系统属性有 'https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html'
 * @version 2016/8/24
 */
@Controller
public class HelloController {
    private static final Logger logger = Logger.getLogger(HelloController.class);

    @Value("${user.name}")
    private String username;

    @Value("${book.name}")
    private String bookName;

    @RequestMapping("/hello")
    public ModelAndView test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        System.out.println(System.getProperty("webapp.root"));

        System.out.println(username);
        System.out.println(bookName);
        ModelAndView m = new ModelAndView();
        m.setViewName("hello");
        m.addObject("name", username);
        m.addObject("book", bookName);

        logger.error("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        return m;
    }
}
