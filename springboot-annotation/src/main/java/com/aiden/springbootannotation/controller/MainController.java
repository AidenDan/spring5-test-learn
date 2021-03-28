package com.aiden.springbootannotation.controller;

import com.aiden.springbootannotation.exector.DefaultPool;
import com.aiden.springbootannotation.service.ConstructorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.*;

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

    private static final ThreadPoolExecutor defaultPool = DefaultPool.getDefaultThreadPoolExecutor();
    private static final Random random = new Random();

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
        for (int i = 0; i < 116; i++) {
            defaultPool.submit(() -> {
                String threadGroupName = Thread.currentThread().getThreadGroup().getName();
                String threadName = Thread.currentThread().getName();
                long threadId = Thread.currentThread().getId();
                log.info("threadGroupName:::{}, threadName:::{}, threadId:::{}", threadGroupName, threadName, threadId);
            });
        }
        defaultPool.shutdown();
        return "success";
    }

    @GetMapping("/testAsync2")
    public Object testAsync2() {
        List<Future<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Future<Integer> feature = defaultPool.submit(() -> {
                String threadGroupName = Thread.currentThread().getThreadGroup().getName();
                String threadName = Thread.currentThread().getName();
                long threadId = Thread.currentThread().getId();
                log.info("threadGroupName:::{}, threadName:::{}, threadId:::{}", threadGroupName, threadName, threadId);
                int nextInt = 100;
                Thread.sleep(500);
                return nextInt;
            });

            // 将计算结果的封装对象存贮到List
            list.add(feature);
        }

        int sum = 0;
        // 遍历List将封装结果对象中的结果取出来求和
        for (Future<Integer> future : list) {
            try {
                // future.get()是一个阻塞方法,如果feature对象中的outcome还没有计算完成,这个方法就会阻塞，直到计算完成为止
                Integer res = future.get();
                sum = sum + res;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.err.println("sum:::" + sum);
        defaultPool.shutdown();
        return "success";
    }

    @GetMapping("/testAsync3")
    public Object testAsync3() {
        int sum2 = 0;
        // 封装批量任务
        List<Callable<Integer>> callableList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            callableList.add(() -> {
                String threadGroupName = Thread.currentThread().getThreadGroup().getName();
                String threadName = Thread.currentThread().getName();
                long threadId = Thread.currentThread().getId();
                log.info("threadGroupName:::{}, threadName:::{}, threadId:::{}", threadGroupName, threadName, threadId);
                int nextInt = 100;
                try {
                    Thread.sleep(500);
                    return nextInt;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            });
        }

        // 批量执行获取结果
        try {
            List<Future<Integer>> futureList = defaultPool.invokeAll(callableList);
            for (Future<Integer> integerFuture : futureList) {
                try {
                    Integer integer = integerFuture.get();
                    sum2 = sum2 + integer;
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            System.err.println("sum2:::" + sum2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "success";
    }

    @GetMapping("/testAsync4")
    public Object testAsync4() {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            return 1;
        }, defaultPool);

        try {
            Integer integer = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
































