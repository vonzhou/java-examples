package com.vonzhou.learn.jvm.chapter8;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;


/*
 * P262 JSR-292 Method Handle �����÷�
 */
public class MethodHandleTest {
	static class ClassA{
		public void println(String s){
			System.out.println(s);
		}
	}
	private static MethodHandle getPrintlnMH(Object receiver) throws NoSuchMethodException, IllegalAccessException{
		MethodType mt = MethodType.methodType(void.class, String.class);
		return lookup().findVirtual(receiver.getClass(), "println", mt).bindTo(receiver);
	}
	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, Throwable {
		Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
		getPrintlnMH(obj).invokeExact("vonzhou");
		
	}
	
	

}
