package com.vonzhou.learn.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * @version 2016/10/14.
 */
public class Main {
    public static void main(String[] args) {
        XStream xstream = new XStream(new StaxDriver());
//        xstream.alias("dog", Dog.class);

        Dog dog = new Dog();
        dog.setName("Huang");
        dog.setCity("Hangzhou");

        String xml = xstream.toXML(dog);
        System.out.println(xml);

        Dog newDog = (Dog)xstream.fromXML(xml);
        System.out.println(newDog);
    }
}
