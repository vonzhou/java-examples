package com.vonzhou.learn.controller;

import com.vonzhou.learn.model.Customer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 参考 `http://www.journaldev.com/2668/spring-validation-example-mvc-validator`
 * 问题：如何控制某个field是数字？
 *
 * TODO
 * 把 ConstraintViolation 解析到一个 JSON 串中，返回给客户端
 *
 * Created by vonzhou on 2016/8/22.
 */
@Controller
public class CustomerController {

    private static final Logger logger = Logger
            .getLogger(CustomerController.class);

    private Map<String, Customer> customers = null;

    public CustomerController() {
        customers = new HashMap<String, Customer>();
    }

    @RequestMapping(value = "/cust/save", method = RequestMethod.GET)
    public String saveCustomerPage(Model model) {
        logger.info("Returning custSave.jsp page");
        model.addAttribute("customer", new Customer());
        return "custSave";
    }

    @RequestMapping(value = "/cust/save.do", method = RequestMethod.POST)
    public String saveCustomerAction(@Valid Customer customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
//                if(bindingResult.hasFieldErrors("phone")){
//                    // 注意第二个参数是 error code
//                    bindingResult.rejectValue("phone", "phone.invalid");
//                }
            logger.info("Returning custSave.jsp page");
            return "custSave";
        }
        logger.info("Returning custSaveSuccess.jsp page");
        model.addAttribute("customer", customer);
        customers.put(customer.getEmail(), customer);
        return "custSaveSuccess";
    }

    @RequestMapping(value = "/cust/show", method = RequestMethod.GET)
    public String show(Model model) {

        Customer customer = new Customer();
        customer.setAge(null);
        customer.setEmail("aaa");

        logger.info(customer);
        logger.info("触发校验规则....");

        /**
         * 触发校验规则
         */
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Customer>> errors = validator.validate(customer);
//        if (errors.size() > 0) {
//            for (ConstraintViolation<Customer> v : errors) {
//                logger.info(v.getMessage() + ", " + v.getPropertyPath() + ", " + v.getInvalidValue());
//            }
//        }
        convertConstraintViolation2Map(errors);

        model.addAttribute("customer", new Customer());
        return "custSaveSuccess";
    }

    public Map<String, String> convertConstraintViolation2Map(Set<ConstraintViolation<Customer>> errors){
        Map<String,String> map = new HashMap<String, String>();
        if (errors.size() > 0) {
            for (ConstraintViolation<Customer> v : errors) {
                map.put(v.getPropertyPath().toString(), v.getMessage());
                logger.info(v.getMessage() + ", " + v.getPropertyPath() + ", " + v.getInvalidValue());
            }
        }

        for(Map.Entry<String, String> entry : map.entrySet()){
            logger.info(entry.getKey() + " : " + entry.getValue());
        }
        return map;
    }
}
