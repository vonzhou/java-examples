package com.vonzhou.learn.other;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @version 2017/2/14.
 */
public class DigestDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String content = "hello i am vonzhou";
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.digest(content.getBytes());

    }
}
