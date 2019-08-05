package com.alimama.server.service;

import com.alimama.api.enums.TopicEnum;
import com.alimama.api.service.IKafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by PengWX on 2019/7/24.
 */
@Service("kafkaProducerService")
public class KafkaProducerServiceImpl implements IKafkaProducerService {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void sendTopicMessage(TopicEnum topicEnum, String message) {
        kafkaTemplate.send(TopicEnum.LOAN.getKey(),message);
    }
}
