package com.learn.spring5ioc.interf;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2020-11-25 22:19:55
 */
public interface UserService {

    default void hello(){
        System.err.println("hello");
    }
}
