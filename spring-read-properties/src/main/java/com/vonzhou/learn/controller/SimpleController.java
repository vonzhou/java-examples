package com.vonzhou.learn.controller;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 可以在 handleRequest 中统一做一些处理
 *
 * @version 2016/8/23.
 */
public class SimpleController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String url = httpServletRequest.getRequestURI();
        int index = url.lastIndexOf(".");
        url = url.substring(0, index);
        System.out.println(url);
        ModelAndView m = new ModelAndView();
        m.setViewName(url);
//        this.getClass().getResourceAsStream("/config.xml")
//
//        this.getClass().getClassLoader().getResourceAsStream("conf.xml")

        return m;
    }
}
