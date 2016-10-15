package com.vonzhou.learn.guava.basics;

import com.google.common.base.Preconditions;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;

/**
 * 前提条件的检查
 * 运行时异常默认的行为是线程终止, 如果捕获的话,就自己控制
 * <p>
 * 常用
 * <p>
 * Created by vonzhou on 16/8/21.
 */
public class PreconditionsDemo {
    public static void checkNotNull() {
        Integer i2 = null;
        i2 = Preconditions.checkNotNull(i2, "不能为空."); // NullPointerException
    }

    public static void checkCondition() {
        String arg = "abc";
        Preconditions.checkArgument(arg.length() > 5, "参数的长度必须大于5！"); // IllegalArgumentException
    }

    public static void checkIndex() {
        Integer[] ia = new Integer[10];
        int index = 10;
        checkElementIndex(index, ia.length);  // IndexOutOfBoundsException
    }

    public static void main(String[] args) {
//        checkNotNull();
//        checkCondition();
        checkIndex();
//        test1();
//        System.out.println("线程" + Thread.currentThread().getId() + "到达这里");
    }
}

