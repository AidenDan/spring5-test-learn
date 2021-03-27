package com.aiden.springbootannotation.controller;

import com.aiden.springbootannotation.exector.DefaultPool;
import com.aiden.springbootannotation.service.ConstructorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2021-3-27 11:10:07
 */

//@ConfigurationProperties(prefix = "my.dev") 通过前缀+属性名称来匹配配置文件中属性
@Slf4j
@RestController
public class MainController {

    @Value("${my.dev.name}")
    private String devName;
    @Value("${my.dev.lock}")
    private String devLock;
    @Value("${my.dev.serial}")
    private String devSerial;

    @Value("${my.prod.name}")
    private String prodName;
    @Value("${my.prod.lock}")
    private String prodLock;
    @Value("${my.prod.serial}")
    private String prodSerial;

    @Value("${my.test.name}")
    private String testName;
    @Value("${my.test.lock}")
    private String testLock;
    @Value("${my.test.serial}")
    private String testSerial;

    private ConstructorService constructorService;
    private List<String> list;
    private Map<String, Object> map;

    private ThreadPoolExecutor defaultPool = DefaultPool.getDefaultThreadPoolExecutor();

    // 以后注入属性必须在构造方法中注入
    @Autowired
    private MainController(ConstructorService constructorService, List<String> list, Map<String, Object> map) {
        this.constructorService = constructorService;
        this.list = list;
        this.map = map;
    }

    @GetMapping("/get")
    public Object get() {
        Map<String, String> map = new HashMap<>();
        map.put("devName", devName);
        map.put("devLock", devLock);
        map.put("devSerial", devSerial);
        map.put("prodName", prodName);
        map.put("prodLock", prodLock);
        map.put("prodSerial", prodSerial);
        map.put("testName", testName);
        map.put("testLock", testLock);
        map.put("testSerial", testSerial);

        constructorService.testCon();

        return map;
    }

    @GetMapping("/testAsync")
    public Object testAsync() {
        for (int i = 0; i < 1000; i++) {
            defaultPool.submit(() -> {
                String threadGroupName = Thread.currentThread().getThreadGroup().getName();
                String threadName = Thread.currentThread().getName();
                long threadId = Thread.currentThread().getId();
                log.info("threadGroupName:::{}, threadName:::{}, threadId:::{}", threadGroupName, threadName, threadId);
            });
        }
        return "success";
    }
}
