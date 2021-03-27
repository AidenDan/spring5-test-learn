package com.learn.spring5aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2020-8-23 23:08:56
 */
@Component
@Aspect
public class AOPController {
    private static final Integer LIMIT_LOGIN_TIMES = 6;

    private Integer loginNum = 0;
    @Around(value = "execution(* com.learn.spring5aop.controller.MainController.testLoginNumbers(..))")
    public Object sumLoginNum(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        for (Object arg : args) {
            System.err.println("方法的参数为:"+arg);
        }
        loginNum = loginNum + 1;
        if (loginNum>LIMIT_LOGIN_TIMES){
            return "访问次数超过6次，请稍后登录......";
        }

        // 执行被增强方法
        Object proceed = point.proceed();
        System.err.println("接口的访问次数达到:"+loginNum);
        System.err.println("被增强方法执行完毕......");
        return proceed;
    }
}
