package com.vonzhou.learn.controller;

import com.vonzhou.learn.annotation.MyAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author vonzhou
 */
@Controller
public class SimpleController {

    @RequestMapping("/hello")
    @MyAnnotation
    public String demo(HttpServletRequest request, HttpServletResponse response){
        return "hello";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }

}
