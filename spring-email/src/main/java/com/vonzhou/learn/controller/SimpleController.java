package com.vonzhou.learn.controller;

import com.vonzhou.learn.util.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @version 2016/8/23.
 */
@Controller
public class SimpleController {

    @RequestMapping("/hello")
    public String sendMail(){
        return "sendEmail";
    }

    @RequestMapping("/sendEmail.do")
    public String sendMailAction(@RequestParam("email") String email){
        if(email != null && email.length() > 0)
            MailSender.sendEmail(email);
        return "sendEmailSuccess";
    }

}
