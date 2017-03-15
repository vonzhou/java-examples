package com.vonzhou.learn.serial;

import java.io.*;

/**
 * @version 2017/2/10.
 */
public class SerializationDemo1 {
    public static void main(String[] args) throws Exception {

        Person p1 = new Person("vonzhou", 1243);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(p1);
        byte[] bytes = os.toByteArray();

        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(is);
        Person person = (Person) in.readObject();
        System.out.println(person);

    }

}
