package com.vonzhou.learn.guava.basics;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.Arrays;

/**
 * 有用的字符串工具类, splitting, joining, padding
 * Joiner 是 immutable 的, 线程安全
 * @see `https://github.com/google/guava/wiki/StringsExplained`
 * Created by vonzhou on 16/8/21.
 */
public class StringsDemo {
    public static void main(String[] args) {
//        f1();
//        f2();
//        f3();
        f4();
    }

    public static void f1(){
        Joiner joiner = Joiner.on("; ").skipNulls();
        String res =  joiner.join("Harry", null, "Ron", "Hermione");
        System.out.println(res);

        //joiner.useForNull("defaultValue"); // 不能修改处理null的规则了,上面已经配置了 skipNulls , 否则 UnsupportedOperationException: already specified skipNulls
        Joiner joiner1 = Joiner.on(", ").useForNull("defaultValue");
        System.out.println(joiner1.join(Arrays.asList("Hello", "fz", null)));
    }

    /**
     * String.split 方法会trail最后的空串
     * guava 的 Splitter 可以 control over all this confusing behavior
     */
    public static void f2(){
        String s = ",a,,b,";
        String[] elments = s.split(",");
        for(String e : elments){
            System.out.println("XXX:" + e);
        }
    }

    public static void f3(){
        Iterable<String> elments =  Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("foo,bar,,   qux,");

        for(String e : elments){
            System.out.println("XXX:" + e);
        }
    }

    public static void f4(){
        Iterable<String> elments =  Splitter.fixedLength(4).split("abdafsafsafasfdsaf");
        for(String e : elments){
            System.out.println("XXX:" + e);
        }
    }
}
