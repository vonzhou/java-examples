package com.vonzhou.learn.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * @version 2016/10/14.
 */
public class Main2 {
    public static void main(String[] args) {
        XStream xstream = new XStream(new StaxDriver());
        xstream.alias("person", Person.class);

        Person joe = new Person("Joe", "Walnes");
        joe.setPhone(new PhoneNumber(123, "1234-456"));
        joe.setFax(new PhoneNumber(123, "9999-999"));

        String xml = xstream.toXML(joe);
        System.out.println(xml);

        Person newJoe = (Person)xstream.fromXML(xml);
        System.out.println(newJoe);
    }
}
