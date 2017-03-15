package com.vonzhou.learn.javabasic;

/**
 * @version 2017/2/23.
 */
public class InnerClassDemo {
    private String a;

    public class Inner {
        public void func() {
            System.out.println(InnerClassDemo.this.a);
        }
    }

    public static void main(String[] args) {
        InnerClassDemo.Inner i = new InnerClassDemo().new Inner();
        i.func();
    }
}
