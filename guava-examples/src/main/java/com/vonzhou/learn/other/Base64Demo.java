package com.vonzhou.learn.other;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @version 2017/2/14.
 */
public class Base64Demo {
    public static void main(String[] args) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String res = base64Encoder.encode("vonzhou".getBytes());
        System.out.println(res);
        BASE64Decoder base64Decoder = new BASE64Decoder();
    }
}
