package com.learn.spring5ioc;

import com.learn.spring5ioc.javaBean.FriendBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2020-8-27 23:36:51
 */
@Configuration
public class SpringConfiguration {

    @Bean(initMethod = "initBean" )
    public FriendBean friendBean(){
        return new FriendBean();
    }
}
