package com.vonzhou.learn.others;

/**
 * @version 2016/10/19.
 */
public class ResourceGetting {
    public static void test1() {
        System.out.println(ResourceGetting.class.getResource(""));
        /**
         * class path 根目录
         */
        System.out.println(ResourceGetting.class.getResource("/"));
    }

    public static void test2() {
        System.out.println(ResourceGetting.class.getResource("/2.txt"));
        ResourceGetting.class.getResourceAsStream("/2.txt");
    }

    /**
     * class.getClassLoader().getResource() 不能以 "/" 开头
       */
    public static void test3() {
        System.out.println(ResourceGetting.class.getClassLoader().getResource("/"));  // null
        System.out.println(ResourceGetting.class.getClassLoader().getResource(""));  // file:/D:/Github/java-examples/jvm-example/target/classes/
    }

    public static void main(String[] args) {
        test3();
    }
}
