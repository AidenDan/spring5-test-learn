package com.learn.spring5ioc.javaBean;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2020-8-20 20:02:40
 */
public class Student implements ImportBeanDefinitionRegistrar {
    private String name;

    public void learn() {
        System.err.println("学有所成。。。");
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
//        AbstractBeanDefinition rootBeanDefinition = new RootBeanDefinition(Student.class);
//        MutablePropertyValues propertyValues = new MutablePropertyValues();
//        propertyValues.add("name", "zz");
//        rootBeanDefinition.setPropertyValues(propertyValues);
//        registry.registerBeanDefinition("student", rootBeanDefinition);
    }

}
