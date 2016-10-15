package com.vonzhou.learn.guava.basics;

import com.google.common.base.Optional;
import com.google.common.base.Strings;

/**
 * 使用 Optional 让你明确考虑 Null,类似 Haskell 的 Maybe 类型
 *
 * @see `https://github.com/google/guava/wiki/UsingAndAvoidingNullExplained`
 * Created by vonzhou on 16/8/21.
 */
public class UsingAndAvoidingNull {
    public static void main(String[] args) {
        Optional<Integer> possible = Optional.of(5);
        possible.isPresent(); // returns true
        possible.get(); // returns 5
        System.out.println(possible.or(-5));


        Optional<Integer> s = Optional.absent();
        if (s.isPresent()) {  // absent
            System.out.println(s.get());
        }

        /**
         *  如果不存在,则使用默认值
         */
        System.out.println(s.or(123));

        Integer i = null;
        Optional<Integer> o1 = Optional.fromNullable(i);
        System.out.println(o1.isPresent());


        /**
         * fromNullable 的反操作,如果不存在就返回 Null
         * */
        Integer i2 = o1.orNull();
        System.out.println(i2 == null);


        String res = Strings.emptyToNull("");
        System.out.println(res);
        System.out.println(Strings.isNullOrEmpty(""));
        String res2 = Strings.nullToEmpty(null);
        System.out.println(res2.length());
    }
}
