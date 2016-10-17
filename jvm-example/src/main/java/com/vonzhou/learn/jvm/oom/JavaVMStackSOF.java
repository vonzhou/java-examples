package com.vonzhou.learn.jvm.oom;


/**
 * P53
 * -Xss128k
 Stack Length : 23178
 Exception in thread "main" java.lang.StackOverflowError
 at com.vonzhou.learn.jvm.oom.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:12)
 at com.vonzhou.learn.jvm.oom.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:12)
 */
public class JavaVMStackSOF {
	private int stackLength=1;
	public void stackLeak(){
		stackLength++;
		stackLeak();
	}
	
	public static void main(String[] args) throws Throwable{
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try{
			oom.stackLeak();
		}catch(Throwable e){
			System.out.println("Stack Length : " + oom.stackLength);
			throw e;
		}
		
	}

}
