package com.vonzhou.learn.controller;

import com.vonzhou.learn.annotation.MyAnnotation;
import com.vonzhou.learn.domain.GenericReturnObject;
import com.vonzhou.learn.service.SimpleException;
import com.vonzhou.learn.service.SimpleService;
import com.vonzhou.learn.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vonzhou
 */
@Controller
public class SimpleController {

    @Autowired
    private SimpleService simpleService;

    @RequestMapping("/test1/{param}")
    @ResponseBody
    public GenericReturnObject test1(@PathVariable String param) throws Exception{
        GenericReturnObject gro = new GenericReturnObject();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", simpleService.srv1(param));
        gro.setData(map);
        gro.setSucceed(true);

        return gro;
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNull(){
        return "null";
    }

    /**
     * 返回视图
     */
//    @ExceptionHandler(SimpleException.class)
//    public String handleSimpleException(){
//        return "fail";
//    }

    /**
     * 返回JSON数据
     */
    @ExceptionHandler(SimpleException.class)
    @ResponseBody
    public void handleSimpleException(SimpleException e, final HttpServletRequest request, HttpServletResponse response){
        GenericReturnObject gro = new GenericReturnObject();
        gro.setSucceed(true);
        gro.setMsg(e.getMessage());
        try {
            response.getWriter().write(JsonUtils.bean2Json(gro));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

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
