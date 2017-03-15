package com.vonzhou.learn.callpython;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 不能传递参数给Python脚本
 * @version 2017/2/5.
 */
public class CallPython1 {
    public static void main(String[] args) {
        try {
            System.out.println("start");
            Runtime runtime = Runtime.getRuntime();
            Process pr = runtime.exec("python D:\\test.py -a");

            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
