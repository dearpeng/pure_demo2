package com.alimama.api.service;

import com.alimama.api.enums.TopicEnum;

/**
 * Created by PengWX on 2019/7/24.
 */
public interface IKafkaProducerService {

    void sendTopicMessage(TopicEnum topicEnum, String message);
}
