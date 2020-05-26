package com.highgreat.sven.aop_module.aspect;

import android.os.SystemClock;
import android.util.Log;

import com.highgreat.sven.aop_module.annotation.BehaviorTrace;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.HashMap;
import java.util.Random;

@Aspect
public class BehaviorTraceAspect { //基于切面编程


    /**
     * 切点  找到处理的位置
     *  将原来的应用中那些注解的地方放到当前切面进行处理
     *  execution(@注解名 注解用的地方)
     *  *  *(..) 处理所有BehaviorTrace注解的地方
     */
    @Pointcut("execution(@com.highgreat.sven.aop_module.annotation.BehaviorTrace * *(..))")
    public void executeBehaviorTrace(){
    }

    /**
     * 对进入切面的内容如何处理
     * @Before 在切入点executeBehaviorTrace()之前运行
     * @After  在切入点executeBehaviorTrace()之后运行
     * @Around  在切入点executeBehaviorTrace()前后都运行
     * @param joinPoint
     * @return
     */
    @Around("executeBehaviorTrace()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e("Sven","Around");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();
        BehaviorTrace behaviorTrace = signature.getMethod().getAnnotation(BehaviorTrace.class);
        String value = behaviorTrace.value();


        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
//        SystemClock.sleep(new Random().nextInt(2000));
        long duration = System.currentTimeMillis() - begin;
        Log.e("Sven", String.format("%s功能：%s类的%s方法执行了，用时%d ms",
                value, className, methodName, duration));
        return result;
    }

}
