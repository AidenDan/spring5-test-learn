package com.aiden.springbootannotation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2021-3-27 11:06:57
 */

@Configuration
@PropertySource(value = {"classpath:my-test-dev.properties", "classpath:my-test-prod.properties", "classpath:my-test-test.properties"})
public class LocalConfiguration {

    // 容器中加入List<String>
    @Bean("list")
    public List<String> list() {
        return new ArrayList<>();
    }

    // 容器中加入Map<String, Object>
    @Bean("map")
    public Map<String, Object> map() {
        return new HashMap<>();
    }
}
