package com.vonzhou.learn.other;

/**
 * @version 2017/2/6.
 */
public class MemoryVisibility {
    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args) throws Exception {
        int count = 0;
        while (true) {
            Thread one = new Thread(new Runnable() {
                public void run() {
                    a = 1;
                    x = b;
                }
            });

            Thread other = new Thread(new Runnable() {
                public void run() {
                    b = 1;
                    y = a;
                }
            });

            one.start();
            other.start();
            one.join();
            other.join();
            System.out.println("第" + count++ + "次(" + x + "," + y + ")");

            if ((x | y) == 0) {
                break;
            }

            x = 0;
            y = 0;
            a = 0;
            b = 0;
        }
    }
}
