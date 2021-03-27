package com.learn.spring5ioc.javaBean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author Aiden
 * @version 1.0
 * @description 生成Star实例的工厂类 通过FactoryBean工厂实例来产生star实例bean
 * @date 2020-8-23 12:41:29
 */
public class FactoryStar implements FactoryBean<Star> {
    @Override
    public Star getObject() throws Exception {
        return new Star();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
