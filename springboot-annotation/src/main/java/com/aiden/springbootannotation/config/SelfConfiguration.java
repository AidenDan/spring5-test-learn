package com.aiden.springbootannotation.config;

import com.aiden.springbootannotation.javabean.Account;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2021-2-19 22:53:13
 */

@Import(Account.class) // Import注解将Account也交给Spring管理
@Configuration
public class SelfConfiguration {

    public String getTest(String s){
        return s;
    }
}
