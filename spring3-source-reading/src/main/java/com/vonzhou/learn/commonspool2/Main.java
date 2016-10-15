package com.vonzhou.learn.commonspool2;

import org.apache.commons.pool2.impl.GenericObjectPool;

import java.io.InputStreamReader;

/**
 * https://commons.apache.org/proper/commons-pool/examples.html
 *
 * @version 2016/9/21.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ReaderUtil2 readerUtil = new ReaderUtil2(new GenericObjectPool<StringBuffer>(new StringBufferFactory()));
        String s = readerUtil.readToString(new InputStreamReader(System.in));
        System.out.println(s);
    }
}
