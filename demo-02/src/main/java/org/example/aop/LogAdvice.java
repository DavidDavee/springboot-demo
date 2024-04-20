package org.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @program: springboot-demo
 * @description:
 * @author: David
 * @create: 2024-04-20 18:29
 **/
@Component
@Aspect
@Slf4j
public class LogAdvice {
    @Before("execution(* org..service.*.*(..))")
    public void before(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LogAdvice.before");
        System.out.println("joinPoint = " + joinPoint);
        log.info(className + ":" + methodName + ".start");
    }
}
