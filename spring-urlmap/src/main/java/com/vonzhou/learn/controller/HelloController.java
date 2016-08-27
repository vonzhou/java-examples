package com.vonzhou.learn.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 可以在 handleRequest 中统一做一些处理
 *
 * @version 2016/8/23.
 */
@org.springframework.stereotype.Controller
public class HelloController{

    @RequestMapping("/hello")
    public ModelAndView test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView m = new ModelAndView();
        m.setViewName("hello");
        System.out.print("dslflksdfmlsdjflsdjkl");
//        this.getClass().getResourceAsStream("/config.xml")
//
//        this.getClass().getClassLoader().getResourceAsStream("conf.xml")

        return m;
    }
}
