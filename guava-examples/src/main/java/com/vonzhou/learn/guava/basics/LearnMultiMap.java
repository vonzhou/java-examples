package com.vonzhou.learn.guava.basics;

import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by vonzhou on 16/3/26.
 */
public class LearnMultiMap {
    public static String[] words = {"hello", "alpha", "vonzhou", "chownvon", "chownvon"};

    /**
     *  use vector , then collections.sort
     */
    public static void wordsSort1(){
        Set<String> set = new TreeSet<String>();
        for(int i=0; i<words.length; i++){
            set.add(words[i]);
        }

        System.out.println(set.toString());

        Iterator<String> iter = set.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }

    /**
     * use guava multimap
     */
    public static void wordsSort2(){
        Multiset<String> set = TreeMultiset.create();
        for(int i=0; i<words.length; i++){
            set.add(words[i]);
        }

        System.out.println(set);

        Iterator<String> iter = set.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }

    public static void main(String[] args){
        wordsSort1();
        System.out.println("--------");
        wordsSort2();
    }
}
