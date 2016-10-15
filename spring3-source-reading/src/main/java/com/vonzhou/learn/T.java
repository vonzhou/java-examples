package com.vonzhou.learn;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @version 2016/9/23.
 */
public class T {
    public static void main(String[] args) throws  Exception{
        File f = new File("C:\\Users\\hzfengzhou\\Desktop\\1.jpeg");
        RandomAccessFile r = new RandomAccessFile(f, "r");
        r.close();
        System.out.println(f.delete());
    }
}
