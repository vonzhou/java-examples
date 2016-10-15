package com.vonzhou.learn.jakson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @version 2016/9/22.
 */
//@JsonIgnoreProperties({"notInterstingMember", "forgetThisField"})
@JsonIgnoreProperties(ignoreUnknown = true)
class MyTestClass {

    private long id;
    private String name;
//    @JsonIgnore
    private String notInterstingMember;
    @JsonProperty("exceptiontype")
    private int anotherMember;
    private int forgetThisField;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotInterstingMember() {
        return this.notInterstingMember;
    }

    public void setNotInterstingMember(String notInterstingMember) {
        this.notInterstingMember = notInterstingMember;
    }

    public int getAnotherMember() {
        return this.anotherMember;
    }

    public void setAnotherMember(int anotherMember) {
        this.anotherMember = anotherMember;
    }

    public int getForgetThisField() {
        return this.forgetThisField;
    }

    public void setForgetThisField(int forgetThisField) {
        this.forgetThisField = forgetThisField;
    }

    @Override
    public String toString() {
        return "MyTestClass [" + this.id + " , " +  this.name + ", " + this.notInterstingMember + ", " + this.anotherMember + ", " + this.forgetThisField + "]";
    }

}


