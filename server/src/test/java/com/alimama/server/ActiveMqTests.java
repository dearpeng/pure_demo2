package com.alimama.server;

import com.alimama.api.enums.TopicEnum;
import com.alimama.api.service.IMqMessageProducerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActiveMqTests {

    @Autowired
    private IMqMessageProducerService mqMessageProducerService;


    @Test
    public void contextLoads() {
        mqMessageProducerService.send( TopicEnum.LOAN,"发送消息测试");
    }

}
