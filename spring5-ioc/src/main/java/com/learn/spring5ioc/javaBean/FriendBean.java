package com.learn.spring5ioc.javaBean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2020-8-27 23:24:38
 */

public class FriendBean implements InitializingBean, DisposableBean, BeanPostProcessor {

    @Value("123")
    private String name;
    public FriendBean(){
        System.err.println("1.创建Bean......");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.println("2.创建Bean赋值属性后调用......");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.err.println("3.Bean初始化前调用postProcessBeforeInitialization......");
        return bean;
    }

    public void initBean(){
        System.err.println("4初始化Bean......");
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.err.println("5.Bean初始化后调用postProcessAfterInitialization......");
        return bean;
    }

    @Override
    public void destroy() throws Exception {
        System.err.println("6.关闭Bean容器后调用......");
    }
}
