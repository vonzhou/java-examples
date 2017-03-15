package com.vonzhou.learn.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * @version 2016/12/9.
 */
public class Test {
    public static String key() {
        String randomUUID = UUID.randomUUID().toString().replace("-", "");
        System.out.println(randomUUID);
        System.out.println(randomUUID.length());
        String uuidMd5 = DigestUtils.md5Hex(randomUUID);
        System.out.println(uuidMd5);

        StringBuilder sb = new StringBuilder();
        sb.append(randomUUID);
        sb.append(uuidMd5.charAt(2));
        sb.append(uuidMd5.charAt(4));
        sb.append(uuidMd5.charAt(8));
        sb.append(uuidMd5.charAt(16));

        return sb.toString();
    }

    public static void main(String[] args) {
        key();
    }
}
