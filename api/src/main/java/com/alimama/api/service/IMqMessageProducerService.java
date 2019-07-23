package com.alimama.api.service;

import com.alimama.api.enums.TopicEnum;

import javax.jms.Destination;
import java.io.Serializable;

/**
 * mq消息发送
 * Created by PengWX on 2019/7/22.
 */
public interface IMqMessageProducerService {


    /**
     * 发布订阅模式
     *
     * @param topic
     * @param message
     */
    void send(TopicEnum topic, String message);

    /**
     * 发布订阅模式
     *
     * @param topic
     * @param message
     */
    void allSend(TopicEnum topic, String message);

    /**
     * 点对点ptp模式
     * 发送到队列
     *
     * @param queue
     * @param message
     */
    void sendQueue(String queue, String message);
}
