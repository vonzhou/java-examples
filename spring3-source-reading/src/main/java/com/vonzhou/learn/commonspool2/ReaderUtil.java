package com.vonzhou.learn.commonspool2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @version 2016/9/21.
 */
public class ReaderUtil {
    public ReaderUtil() {
    }

    /**
     * Dumps the contents of the {@link Reader} to a
     * String, closing the {@link Reader} when done.
     */
    public String readToString(Reader in) throws IOException {
        StringBuffer buf = new StringBuffer();
        try {
            for (int c = in.read(); c != -1; c = in.read()) {
                buf.append((char) c);
            }
            return buf.toString();
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                // ignored
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String s = new ReaderUtil().readToString(new InputStreamReader(System.in));
        System.out.println(s);
    }
}
