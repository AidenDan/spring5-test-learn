package com.learn.springbootkafkaconsumer.kafka.consumer;

import com.learn.springbootkafkaconsumer.kafka.utils.CacheUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2020-8-26 22:36:14
 */
@Component
public class KafkaConsumer {
    @Autowired
    KafkaTemplate kafkaTemplate;

    private static final Logger log= LoggerFactory.getLogger(KafkaConsumer.class);

    /**
     *     //声明consumerID为demo，监听topicName为topic.quick.demo的Topic
     *
     * @param msgData
     */
    @KafkaListener(id = "demo", topics = "topic.quick.demo")
    public void listen(String msgData) {
        CacheUtils.setCacheData(msgData);

        // 继续往消息队列发消息
//        kafkaTemplate.send("topic01", msgData);

        log.info("demo receive : "+msgData);
    }
}
