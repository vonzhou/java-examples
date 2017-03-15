package com.vonzhou.learn.serial;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @version 2017/2/10.
 */
public class HessianDemo {
    public static void main(String[] args) throws Exception {
        Person p1 = new Person("vonzhou", 1243);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Hessian2Output out = new Hessian2Output(os);
        out.writeObject(p1);
        out.close(); // 要close否则 java.io.EOFException: readObject: unexpected end of file
        byte[] bytes = os.toByteArray();

        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        Hessian2Input in = new Hessian2Input(is);
        Person person = (Person) in.readObject();
        System.out.println(person);
    }
}
