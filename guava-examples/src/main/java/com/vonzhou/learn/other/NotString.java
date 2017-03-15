package com.vonzhou.learn.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @version 2016/12/16.
 */
public class NotString {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> answers = notString(sc);
        for (String s : answers)
            System.out.println(s);

    }

    public static List<String> notString(Scanner sc) {
        int tries = 6;
        List<String> res = new ArrayList<String>();
        for (int i = 0; i <= tries; i++) {
            System.out.println("Input Name: ");
            String name = sc.nextLine();
            if (name.startsWith("not")) {
                res.add(name);
            } else
                res.add("not" + name);
        }
        return res;
    }

}
