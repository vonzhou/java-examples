package com.vonzhou.learn.guava.basics;

/**
 * @version 2016/10/9.
 */
public class VersionCmp {
    public static int versionCompare2(String str1, String str2) {
        String[] vals1 = str1.split("\\.");
        String[] vals2 = str2.split("\\.");
        int i = 0;
        for(int j = 0;j < vals2.length; j++){
            System.out.println(vals2[j]);
        }
        // set index to first non-equal ordinal or length of shortest version string
        while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) {
            i++;
        }
        // compare first non-equal ordinal number
        if (i < vals1.length && i < vals2.length) {
            int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
            return Integer.signum(diff);
        }
        // the strings are equal or one string is a substring of the other
        // e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
        return Integer.signum(vals1.length - vals2.length);
    }

    public static void main(String[] args) {
        System.out.println(versionCompare("1.0.1", "1.0.1"));
        System.out.println(versionCompare("1", "1.0.1"));
//        System.out.println(versionCompare("1.0.0", "1.0.1"));
        System.out.println(versionCompare("1.0.2", "1.0.1"));


        System.out.println("abc".equals(null));
    }

    public static int versionCompare(String version1, String version2) {
        String[] verNums1 = version1.split("\\.");
        String[] verNums2 = version2.split("\\.");
        int i = 0;
        while (i < verNums1.length && i < verNums2.length && verNums1[i].equals(verNums2[i])) {
            i++;
        }
        if (i < verNums1.length && i < verNums2.length) {
            int diff = Integer.valueOf(verNums1[i]).compareTo(Integer.valueOf(verNums2[i]));
            return Integer.signum(diff);
        }
        return Integer.signum(verNums1.length - verNums2.length);
    }
}
