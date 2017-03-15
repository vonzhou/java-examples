package com.vonzhou.learn.other;

/**
 * @version 2017/1/18.
 */
public class RecursiveSum {
    public static void main(String[] args) {
        System.out.println(sum(new double[] {1,3,4,5}));
    }

    public static double sum(double[] a) {
        if (a.length == 0)
            return 0.0;
        else{
            return sumHelper(a, 0);
        }
    }

    private static double sumHelper(double[] a, int i) {
        if(a.length - 1 == i){
            return a[i];
        }else{
            return  a[i] + sumHelper(a, i + 1);
        }
    }
}
