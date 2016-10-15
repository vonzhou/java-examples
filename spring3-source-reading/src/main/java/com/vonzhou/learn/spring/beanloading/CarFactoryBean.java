package com.vonzhou.learn.spring.beanloading;

import org.springframework.beans.factory.FactoryBean;

/**
 * @version 2016/9/20.
 */
public class CarFactoryBean implements FactoryBean<Car> {
    private String carInfo;

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }

    public Car getObject() throws Exception {
        Car car = new Car();
        String[] fields = this.carInfo.split(",");
        car.setBrand(fields[0]);
        car.setMaxSpeed(Integer.valueOf(fields[1]));
        car.setPrice(Double.valueOf(fields[2]));
        return car;
    }

    public Class<?> getObjectType() {
        return Car.class;
    }

    public boolean isSingleton() {
        return false;
    }
}
