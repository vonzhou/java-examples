package com.vonzhou.learn.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 2017/1/18.
 */
public class StringList {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("birthday54", "abac1234", "hello12431");
        list = splitup(list);
        for (String s : list)
            System.out.println(s);

    }

    public static List<String> splitup(List<String> ss) {
        List<String> res = new ArrayList<>();
        for (String s : ss) {
            int pos = firstDigitPos(s);
            if (pos != -1) {
                res.add(s.substring(0, pos));
                res.add(s.substring(pos));
            } else {
                res.add(s);
            }

        }
        return res;
    }

    private static int firstDigitPos(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
                return i;
        }
        return -1;
    }
}
