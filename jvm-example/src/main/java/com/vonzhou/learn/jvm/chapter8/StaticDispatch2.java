package com.vonzhou.learn.jvm.chapter8;

/*
 * P247   Method Overload Resolution
 */
public class StaticDispatch2 {
	class Human{}
	
	class Man extends Human{
		@Override
		public String toString() {
			return "MAN";
		}
	}
	
	class Woman extends Human{
		@Override
		public String toString() {
			return "WOMAN";
		}
	}
	
	public void sayHello(Human guy){
		System.out.println("hello, guy!" + guy.toString());
	}
	public void sayHello(Man guy){
		System.out.println("hello, gentleman!");
	}
	public void sayHello(Woman guy){
		System.out.println("hello, lady!");
	}
	
	public static void main(String[] args) {
		StaticDispatch2 sr = new StaticDispatch2();
		Human man = sr.new Man();
		Human woman = sr.new Woman();
		sr.sayHello(man);
		sr.sayHello(woman);
	}

}
