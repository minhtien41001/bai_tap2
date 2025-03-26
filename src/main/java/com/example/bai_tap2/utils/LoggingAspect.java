package com.example.bai_tap2.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Áp dụng aspect cho tất cả các phương thức trong các controller
    @Around("execution(* com.example.bai_tap2.controller.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // Lấy thời gian bắt đầu
        long start = System.currentTimeMillis();

        // Thực thi phương thức API
        Object proceed = joinPoint.proceed();

        // Lấy thời gian kết thúc và tính thời gian thực thi
        long executionTime = System.currentTimeMillis() - start;

        // Ghi log tên phương thức và thời gian thực thi
        logger.info("API [{}] thực thi trong {} ms",
                joinPoint.getSignature(), executionTime);

        return proceed;
    }
}