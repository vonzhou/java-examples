package com.vonzhou.learn.commonslang;

import org.apache.commons.lang3.StringUtils;

/**
 * @version 2016/10/19.
 */
public class FuzzySearchDemo {
    public static void main(String[] args) {
        String[] lines = {"abcdefg", "belldash", "aljfas;jfa;fjas;dfjwqeotruwqeurwqoperuqwpurqwpruqwepr", "begaaaaaaaaa"};
        String input = "beg";
        for(int i=0; i<lines.length; i++){
            int d = StringUtils.getLevenshteinDistance(input, lines[i]);
            System.out.println(d);

        }
    }

}
