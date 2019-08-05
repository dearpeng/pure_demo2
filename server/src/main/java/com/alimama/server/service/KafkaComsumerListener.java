package com.alimama.server.service;

import com.alimama.api.enums.TopicEnum;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by PengWX on 2019/7/24.
 */
@Component
public class KafkaComsumerListener {
    @KafkaListener(topics = "queues.loan.test", groupId = "queues.loan.test")
    public void receiveTopicMessage(String message){
        System.out.println("KafkaConsumer1 ---> 11111111111111111111：接收订阅者模式发送的消息，内容为：" + message);
    }
    @KafkaListener(topics = "queues.loan.test", groupId = "queues.loan.test")
    public void receiveTopicMessageTwo(String message){
        System.out.println("KafkaConsumer2 ---> 22222222222222222222：接收订阅者模式发送的消息，内容为：" + message);
    }
}
