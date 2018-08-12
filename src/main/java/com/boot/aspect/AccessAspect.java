package com.boot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component("AccessAspect")
@Aspect
public class AccessAspect {

    @Pointcut("@annotation(com.boot.annotation.AccessJ)")
    public void startCheckPointcut()
    {}

    @Before("startCheckPointcut()")
    public void startCheck(JoinPoint joinPoint){
        System.out.print("dddddddddddddddddd");
    }
}
