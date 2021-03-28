package com.aiden.springbootannotation.exector;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2021-3-27 12:36:42
 */

public class DefaultPool {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 默认线程池配置
     * 最大线程数量设置为多少,必须考虑并发量,最大任务数可能是多少
     * @return
     */
    public static ThreadPoolExecutor getDefaultThreadPoolExecutor() {
        ThreadPoolExecutor defaultThreadPoolExecutor = new ThreadPoolExecutor(9,
                16,
                1000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1000),
                (runner) -> new Thread(new ThreadGroup("my-thread-group"), runner, "thread-name-" + atomicInteger.getAndIncrement()),
                (runner, poolExecutor) -> {
                    throw new RuntimeException("pool is maxing");
                }
        );
        return defaultThreadPoolExecutor;
    }
}
