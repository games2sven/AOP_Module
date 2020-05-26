package com.highgreat.sven.aop_module.aspect;

import android.content.Context;
import android.util.Log;

import com.highgreat.sven.aop_module.annotation.BehaviorTrace;
import com.highgreat.sven.aop_module.annotation.LoginFilter;
import com.highgreat.sven.aop_module.exception.AnnotationException;
import com.highgreat.sven.aop_module.exception.NoInitException;
import com.highgreat.sven.aop_module.login.ILogin;
import com.highgreat.sven.aop_module.login.LoginAssistant;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class LoginFilterAspect { //基于切面编程


    /**
     * 切点  找到处理的位置
     *  将原来的应用中那些注解的地方放到当前切面进行处理
     *  execution(@注解名 注解用的地方)
     *  *  *(..) 处理所有BehaviorTrace注解的地方
     */
    @Pointcut("execution(@com.highgreat.sven.aop_module.annotation.LoginFilter * *(..))")
    public void executeLoginFilter(){
    }

    /**
     * 对进入切面的内容如何处理
     * @Before 在切入点executeBehaviorTrace()之前运行
     * @After  在切入点executeBehaviorTrace()之后运行
     * @Around  在切入点executeBehaviorTrace()前后都运行
     * @param joinPoint
     * @return
     */
    @Around("executeLoginFilter()")
    public void weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        //在执行LoginFilter注解的方法之前先走这里
        //判断是否登录
        Log.e("Sven","loginFilter");
        ILogin iLogin = LoginAssistant.getInstance().getiLogin();
        if (iLogin == null) {
            throw new NoInitException("LoginSDK 没有初始化！");
        }
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new AnnotationException("LoginFilter 注解只能用于方法上");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        LoginFilter loginFilter = methodSignature.getMethod().getAnnotation(LoginFilter.class);
        if (loginFilter == null) {
            return;
        }

        Context param = LoginAssistant.getInstance().getApplicationContext();
        if(iLogin.isLogin(param)){
            //已经登录，执行接下来的LoginFilter注解下的代码
            joinPoint.proceed();
        }else{
            //没有登录，去登录
            iLogin.login(param,loginFilter.userDefine());
        }
    }

}
