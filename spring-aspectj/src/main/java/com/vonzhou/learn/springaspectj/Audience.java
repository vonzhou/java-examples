package com.vonzhou.learn.springaspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 观众切面
 */
@Aspect
public class Audience {
    @Pointcut(
            "execution(* com.vonzhou.learn.springaspectj.Performer.perform(..))")
    public void performance() {
    }

    @Before("performance()")
    public void takeSeats() {
        System.out.println("观众入座...");
    }

    @Before("performance()")
    public void turnOffCellPhones() {
        System.out.println("观众关闭手机..");
    }

    @AfterReturning("performance()")
    public void applaud() {
        System.out.println("观众鼓掌...");
    }

    @AfterThrowing("performance()")
    public void demandRefund() {
        System.out.println("Oops,什么表演啊?");
    }

    @Around("performance()")
    public void around(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("before around ====");
        try {
            proceedingJoinPoint.proceed();
            System.out.println("after around ===");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}