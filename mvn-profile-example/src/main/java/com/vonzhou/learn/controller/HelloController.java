package com.vonzhou.learn.controller;

import com.vonzhou.learn.util.EnvironmentUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author vonzhou
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public ModelAndView test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        ModelAndView m = new ModelAndView();
        m.setViewName("hello");
        m.addObject("name", "vonzhou");
        m.addObject("env", EnvironmentUtil.getEnv());

        return m;
    }
}
