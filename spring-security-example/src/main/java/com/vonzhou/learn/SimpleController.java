package com.vonzhou.learn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version 2016/11/8.
 */
@Controller
@RequestMapping(value = "/module")
public class SimpleController {

    @RequestMapping(value = "/index")
    public String home(){
        return "index";
    }
}
