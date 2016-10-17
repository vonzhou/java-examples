package com.vonzhou.learn.jvm.gc;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=1
 * -XX:+PrintTenuringDistribution
 * -XX:+UseSerialGC   显示指定

 [GC (Allocation Failure) [DefNew
 Desired survivor size 524288 bytes, new threshold 1 (max 1)
 - age   1:     898736 bytes,     898736 total
 : 5835K->877K(9216K), 0.0030132 secs] 5835K->4973K(19456K), 0.0030581 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 [GC (Allocation Failure) [DefNew
 Desired survivor size 524288 bytes, new threshold 1 (max 1)
 : 4973K->0K(9216K), 0.0009428 secs] 9069K->4973K(19456K), 0.0009582 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 Heap
 def new generation   total 9216K, used 4260K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
 eden space 8192K,  52% used [0x00000000fec00000, 0x00000000ff0290f0, 0x00000000ff400000)
 from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
 to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
 tenured generation   total 10240K, used 4973K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
 the space 10240K,  48% used [0x00000000ff600000, 0x00000000ffadb6c0, 0x00000000ffadb800, 0x0000000100000000)
 Metaspace       used 3005K, capacity 4494K, committed 4864K, reserved 1056768K
 class space    used 329K, capacity 386K, committed 512K, reserved 1048576K


 * -XX:MaxTenuringThreshold=15

 [GC (Allocation Failure) [DefNew
 Desired survivor size 524288 bytes, new threshold 1 (max 15)
 - age   1:     898856 bytes,     898856 total
 : 5999K->877K(9216K), 0.0026244 secs] 5999K->4973K(19456K), 0.0026507 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 [GC (Allocation Failure) [DefNew
 Desired survivor size 524288 bytes, new threshold 15 (max 15)
 : 4973K->0K(9216K), 0.0013541 secs] 9069K->4973K(19456K), 0.0013695 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
 Heap
 def new generation   total 9216K, used 4150K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
 eden space 8192K,  50% used [0x00000000fec00000, 0x00000000ff00dbf8, 0x00000000ff400000)
 from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
 to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
 tenured generation   total 10240K, used 4973K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
 the space 10240K,  48% used [0x00000000ff600000, 0x00000000ffadb738, 0x00000000ffadb800, 0x0000000100000000)
 Metaspace       used 3007K, capacity 4494K, committed 4864K, reserved 1056768K
 class space    used 330K, capacity 386K, committed 512K, reserved 1048576K

 *
 * @version 2016/10/16.
 */
public class TestAllocation3 {
    private static final int _1MB = 1024 * 1024;

    public static void testTenuringThreshold(){
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;

        allocation3 = new byte[4 * _1MB];

    }

    public static void main(String[] args) {
        testTenuringThreshold();
    }
}
