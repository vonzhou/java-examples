package com.vonzhou.learn.validator;

import com.vonzhou.learn.model.Employee;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @see com.vonzhou.learn.model.Customer
 * Created by vonzhou on 2016/8/22.
 */
public class EmployeeFormValidator implements Validator {

    //which objects can be validated by this validator
    public boolean supports(Class<?> paramClass) {
        return Employee.class.equals(paramClass);
    }

    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");

        /**
         * 如果没有 id 为 空 的错误之时，才进一步校验是否非负，否则 NumberFormatException
         * 然而没有用
         * 因为把表单绑定到对象的行为，在进入该方法之前已经发生了
         * 对于使用 Hibernate Validator 也会如此
         * 对于该问题的方法可以使用 @Pattern
         */
        System.out.println(errors.hasFieldErrors("id"));
        if (!errors.hasFieldErrors("id")) {
            Employee emp = (Employee) obj;
            if (emp.getId() <= 0) {
                errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
            }
        }


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "role.required");
    }
}
