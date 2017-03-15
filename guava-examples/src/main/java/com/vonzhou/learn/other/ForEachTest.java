package com.vonzhou.learn.other;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @version 2017/1/22.
 */
public class ForEachTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c");
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String s = iterator.next();
            if(s.equals("c"))
                System.out.println(iterator.next());
        }

    }
}
