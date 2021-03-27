package com.learn.springbootkafka.kafka.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2020-8-26 22:26:06
 */
@RestController
public class KafkaProvider {
    @Autowired
    KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "sendMsg")
    public String sendMsg(){
        kafkaTemplate.send("topic.quick.demo", "this is my first demo");
        return "OK";
    }
}
