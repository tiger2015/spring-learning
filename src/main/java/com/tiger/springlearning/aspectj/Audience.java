package com.tiger.springlearning.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
@Slf4j
public class Audience {
    @Pointcut("execution(* com.tiger.springlearning.aspectj.Performance.perform(..))")
    public void performance() {
    }

    @Before("performance()")
    public void silencePhone() {
        log.info("silence cell phone");
    }

    @Before("performance()")
    public void takeSeat() {
        log.info("take seat");
    }

    @AfterReturning("performance()")
    public void applause() {
        log.info("clap! clap! clap!");
    }

    @AfterThrowing("performance()")
    public void demandRefund() {
        log.info("demand a refund");
    }

    @Around("performance()")
    public void watchPerformance(ProceedingJoinPoint joinPoint){

        log.info("silence cell phones");
        log.info("take seat");
        try {
            joinPoint.proceed();
            log.info("clip! clip! clip!");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

}
