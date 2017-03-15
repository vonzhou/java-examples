package com.vonzhou.learn.javabasic;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 2017/2/21.
 */
public class ForeachRemove {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");

        for(String tmp : list){
            if("1".equals(tmp))  // 如果换成"2".equals(tmp)      java.util.ConcurrentModificationException
                list.remove(tmp);
        }
    }
}
