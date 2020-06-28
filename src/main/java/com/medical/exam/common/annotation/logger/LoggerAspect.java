package com.medical.exam.common.annotation.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/01
 */
@Aspect
@Component
public class LoggerAspect {

    @Pointcut("@within(com.medical.exam.common.annotation.logger.Logger)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        LoggerHolder.setLogger(joinPoint.getSignature().getDeclaringType());
    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        LoggerHolder.clearLogger();
    }
}
