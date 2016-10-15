//package com.vonzhou.learn.jvm.chapter8;
//
//
//import java.lang.invoke.CallSite;
//import java.lang.invoke.ConstantCallSite;
//import java.lang.invoke.MethodHandles;
//import java.lang.invoke.MethodType;
//
///*
// * invokedynamic instruction show
// */
//public class InvokeDynamicTest {
//
//	public static void main(String[] args) {
//		INDY_BootstrapMethod.invokeExact("vonzhou");
//	}
//	public static void testMethod(String s){
//		System.out.println("hello String : " + S);
//	}
//	public static CallSite BootstrapMehod(MethodHandles.Lookup lookup, String name,
//										  MethodType mt) throws NoSuchMethodException, IllegalAccessException{
//		return new ConstantCallSite(lookup.findStatic(InvokeDynamicTest.class, name, mt));
//	}
//
//	private static MethodType MT_BootstrapMethod(){
//		return MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;"
//				+ "Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;", null);
//	}
//
//
//}
