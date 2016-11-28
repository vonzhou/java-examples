package com.vonzhou.learn.model;


import com.google.common.base.MoreObjects;
import com.vonzhou.learn.validator.Phone;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * Created by vonzhou on 2016/8/22.
 */
public class Customer {

    @NotEmpty
    @Size(min = 2, max = 30)
    private String name;

    @NotEmpty
    @Email(message = "电子邮件地址不合法")
    private String email;

    @NotNull
    @Min(18)
    @Max(100)
    private Integer age;

    @NotNull
    private Gender gender;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @NotNull
    @Past
    private Date birthday;

    @Phone
    private String phone;

    public enum Gender {
        MALE, FEMALE
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", this.name)
                .add("email", this.email)
                .add("age", this.age)
                .add("gender", this.gender)
                .add("birthday", this.birthday)
                .add("phone", this.phone)
                .toString();
    }
}
