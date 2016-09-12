package com.vonzhou.learn.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by vonzhou on 2016/8/19.
 */
@Aspect
public class CheckAspect {

    @Around("execution(* com.vonzhou.learn.controller.SimpleController.*(..)) && @annotation(myAnnotation)")
    public Object checkHttpRequest(ProceedingJoinPoint joinPoint, MyAnnotation myAnnotation) {
        HttpServletRequest request = getHttpRequest(joinPoint);
        HttpServletResponse response = getHttpResponse((joinPoint));
        /**
         * 根据请求做一些处理
         */
        System.out.println("正在对你的请求做检查. " + request.getRequestURL());
        boolean isValid = true;
        if (isValid) {
            System.out.println("请求检查通过!");
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                System.out.println("转交到Controller handler失败");
            }
        }
        /**
         * 对请求作出回应, 校验未通过
         */
        return null;

    }

    private HttpServletRequest getHttpRequest(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for (Object obj : args) {
                if (obj instanceof HttpServletRequest)
                    return (HttpServletRequest) obj;
            }
        }
        return null;
    }

    private HttpServletResponse getHttpResponse(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for (Object obj : args) {
                if (obj instanceof HttpServletResponse)
                    return (HttpServletResponse) obj;
            }
        }
        return null;
    }


}
