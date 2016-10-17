package com.vonzhou.learn.jvm.gc;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8

 Heap
 PSYoungGen      total 9216K, used 5907K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
 eden space 8192K, 72% used [0x00000000ff600000,0x00000000ffbc4d60,0x00000000ffe00000)
 from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
 to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
 ParOldGen       total 10240K, used 0K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
 object space 10240K, 0% used [0x00000000fec00000,0x00000000fec00000,0x00000000ff600000)
 Metaspace       used 3024K, capacity 4494K, committed 4864K, reserved 1056768K
 class space    used 331K, capacity 386K, committed 512K, reserved 1048576K

 * -XX:PretenureSizeThreshold=3145728  因为默认是PS + ParOld 收集算法，所以没有效果
 *
 * -XX:+UseSerialGC

 Heap
 def new generation   total 9216K, used 1811K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
 eden space 8192K,  22% used [0x00000000fec00000, 0x00000000fedc4d50, 0x00000000ff400000)
 from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
 to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
 tenured generation   total 10240K, used 4096K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
 the space 10240K,  40% used [0x00000000ff600000, 0x00000000ffa00010, 0x00000000ffa00200, 0x0000000100000000)
 Metaspace       used 2936K, capacity 4494K, committed 4864K, reserved 1056768K
 class space    used 323K, capacity 386K, committed 512K, reserved 1048576K

 * @version 2016/10/16.
 */
public class TestAllocation2 {
    private static final int _1MB = 1024 * 1024;

    public static void testPretenureSizeThreshold(){
        byte[] allocation;
        allocation = new byte[4 * _1MB];

    }

    public static void main(String[] args) {
        testPretenureSizeThreshold();
    }
}
