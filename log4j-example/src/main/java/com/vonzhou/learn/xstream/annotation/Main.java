package com.vonzhou.learn.xstream.annotation;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
/**
 * @version 2016/10/14.
 */
public class Main {
    public static void main(String[] args) {
        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(Dog.class);

        Dog dog = new Dog();
        dog.setName("Huang");
        dog.setCity("Hangzhou");
        dog.setColor("yellow");

        String xml = xstream.toXML(dog);
        System.out.println(xml);

        Dog newDog = (Dog) xstream.fromXML(xml);
        System.out.println(newDog);
    }
}
