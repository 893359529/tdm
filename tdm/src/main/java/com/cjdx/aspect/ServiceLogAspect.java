package com.cjdx.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.awt.*;


@Component
@Aspect
public class ServiceLogAspect {

    public static final Logger log = LoggerFactory.getLogger(ServiceLogAspect.class);

    /*
     * AOP通知：
     * 1.前置通知：方法调用之前通知
     * 2.后置通知：方法正常调用之后通知
     * 3.环绕通知：方法调用前后都分别进行通知
     * 4.异常通知：方法调用过程中发生异常，则通知
     * 5.最终通知：在方法调用之后执行
     */

    /**
     * 切面表达式
     * excution 代表所要执行的表达式主体
     * 第一处 * 表示方法返回类型 *代表所有类型
     * 第二处 包名代表aop监控的类所在的包
     * 第三处  .. 代表该包及子包下所有类方法
     * 第四处 * 代表类名 *代表所有类
     * 第五处 *(..) *代表类中的方法名，(..)表示方法中的任何参数
     * @Description
     * @Param: [joinPoint]
     * @Return: java.lang.Object
     * @author: turbo
     * @date: 2020/4/22
     * @time: 21:30
     */
    @Around("execution(* com.cjdx.service.impl..*.*(..))")
     public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("===== 开始执行 {}.{}=====",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName());

        // 记录开始时间
        long begin = System.currentTimeMillis();
        //执行目标service
        Object result = joinPoint.proceed();
        // 记录结束时间
        long end = System.currentTimeMillis();

        long takeTime = end - begin;

        if (takeTime > 3000) {
            log.error("===== 执行结束，耗时：{} 毫秒 ======",takeTime);
        }else if (takeTime > 2000) {
            log.warn("===== 执行结束，耗时：{} 毫秒 ======",takeTime);
        }else {
            log.info("===== 执行结束，耗时：{} 毫秒 ======",takeTime);
        }

        return result;
    }
}
