package com.vonzhou.learn.other;

/**
 * @version 2017/1/18.
 */
public class Test1 {
    static class Baap {
        public int h = 4;
        public int getH() {
            System.out.println("Baap " + h);
            return h;
        }
    }

    static class Beta extends Baap {
        public int h = 44;
        public int getH() {
            System.out.println("Beta " + h);
            return h;
        }
    }

    public static void main(String[] args) {
        Baap b = new Beta();
        System.out.print(b.h + " ");
        System.out.print(b.getH());
        //Beta bb = (Beta) b;
        //System.out.println(bb.h + " " + bb.getH());
    }

}
