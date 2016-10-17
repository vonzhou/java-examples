package com.vonzhou.learn.jvm.gc;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=15
 * -XX:+PrintTenuringDistribution
 * -XX:+UseSerialGC   显示指定

 [GC (Allocation Failure) [DefNew
 Desired survivor size 524288 bytes, new threshold 1 (max 1)
 - age   1:     898928 bytes,     898928 total
 : 5999K->877K(9216K), 0.0029237 secs] 5999K->4973K(19456K), 0.0191852 secs] [Times: user=0.02 sys=0.00, real=0.02 secs]
 [GC (Allocation Failure) [DefNew
 Desired survivor size 524288 bytes, new threshold 1 (max 1)
 - age   1:      13808 bytes,      13808 total
 : 5055K->13K(9216K), 0.0016989 secs] 9151K->4987K(19456K), 0.0017319 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 Heap
 def new generation   total 9216K, used 4245K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
 eden space 8192K,  51% used [0x00000000fec00000, 0x00000000ff021e80, 0x00000000ff400000)
 from space 1024K,   1% used [0x00000000ff400000, 0x00000000ff4035f0, 0x00000000ff500000)
 to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
 tenured generation   total 10240K, used 4973K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
 the space 10240K,  48% used [0x00000000ff600000, 0x00000000ffadb638, 0x00000000ffadb800, 0x0000000100000000)
 Metaspace       used 3145K, capacity 4494K, committed 4864K, reserved 1056768K
 class space    used 346K, capacity 386K, committed 512K, reserved 1048576K


 * -XX:MaxTenuringThreshold=15

 [GC (Allocation Failure) [DefNew
 Desired survivor size 524288 bytes, new threshold 1 (max 15)
 - age   1:     905760 bytes,     905760 total
 : 5999K->884K(9216K), 0.0043874 secs] 5999K->4980K(19456K), 0.0044189 secs] [Times: user=0.00 sys=0.02, real=0.00 secs]
 [GC (Allocation Failure) [DefNew
 Desired survivor size 524288 bytes, new threshold 15 (max 15)
 - age   1:       4472 bytes,       4472 total
 : 5062K->4K(9216K), 0.0012950 secs] 9158K->4976K(19456K), 0.0013175 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 Heap
 def new generation   total 9216K, used 4236K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
 eden space 8192K,  51% used [0x00000000fec00000, 0x00000000ff021f78, 0x00000000ff400000)
 from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff401178, 0x00000000ff500000)
 to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
 tenured generation   total 10240K, used 4971K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
 the space 10240K,  48% used [0x00000000ff600000, 0x00000000ffadaef0, 0x00000000ffadb000, 0x0000000100000000)
 Metaspace       used 3129K, capacity 4494K, committed 4864K, reserved 1056768K
 class space    used 345K, capacity 386K, committed 512K, reserved 1048576K

 *
 * @version 2016/10/16.
 */
public class TestAllocation4 {
    private static final int _1MB = 1024 * 1024;

    public static void testTenuringThreshold2(){
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB / 4];

        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;

        allocation4 = new byte[4 * _1MB];

    }

    public static void main(String[] args) {
        testTenuringThreshold2();
    }
}
