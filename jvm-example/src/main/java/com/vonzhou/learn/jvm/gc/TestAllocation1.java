package com.vonzhou.learn.jvm.gc;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8

 [GC (Allocation Failure) [PSYoungGen: 5743K->744K(9216K)] 5743K->4848K(19456K), 0.1769879 secs] [Times: user=0.00 sys=0.00, real=0.19 secs]
 Heap
 PSYoungGen      total 9216K, used 8203K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
 eden space 8192K, 91% used [0x00000000ff600000,0x00000000ffd48dc0,0x00000000ffe00000)
 from space 1024K, 72% used [0x00000000ffe00000,0x00000000ffeba020,0x00000000fff00000)
 to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
 ParOldGen       total 10240K, used 4104K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
 object space 10240K, 40% used [0x00000000fec00000,0x00000000ff002020,0x00000000ff600000)
 Metaspace       used 3074K, capacity 4494K, committed 4864K, reserved 1056768K
 class space    used 336K, capacity 386K, committed 512K, reserved 1048576K

 * @version 2016/10/16.
 */
public class TestAllocation1 {
    private static final int _1MB = 1024 * 1024;

    public static void testAllocation(){
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[3 * _1MB];
        allocation4 = new byte[4 * _1MB]; // 出现一次 minor GC

    }

    public static void main(String[] args) {
        testAllocation();
    }
}
