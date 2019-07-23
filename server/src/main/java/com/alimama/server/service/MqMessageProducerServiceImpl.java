package com.alimama.server.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alimama.api.enums.TopicEnum;
import com.alimama.api.service.IMqMessageProducerService;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.jms.*;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by PengWX on 2019/7/22.
 */
@Service("mqMessageProducerService")
public class MqMessageProducerServiceImpl implements IMqMessageProducerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MqMessageProducerServiceImpl.class);

    private Executor executor = new ThreadPoolExecutor(1, 10, 1, TimeUnit.DAYS, new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;


    /**
     * 点对点和发布订阅都可以用
     * @param topic
     * @param message
     */
    @Override
    public void allSend(TopicEnum topic, String message) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                jmsTemplate.send(topic.getKey(), new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        TextMessage tm = session.createTextMessage();
                        tm.setText(JSON.toJSONString(message, SerializerFeature.WriteClassName));
                        LOGGER.info("send message to topic:" + topic.getKey() + ",content:" + message);
                        return tm;
                    }
                });
            }
        });
    }

    /**
     * 发布订阅模式,在点对点模式没配置containerFactory不能用
     * @param topic
     * @param message
     */
    @Override
    public void send(TopicEnum topic, String message) {
        if (!StringUtils.isEmpty(message)) {
            executor.execute(new Runnable() {
                @Override
                public void run() {

                    /**
                     * 将接受到的消息及消息模式(topic或queue)放到队列里面，然后消费
                     * 者只需要正确的添加注解@JmsListener（destination = "目的地"），监听队列消息就会主动获取
                     */
                    ActiveMQTopic mqTopic = new ActiveMQTopic(topic.getKey());
                    jmsMessagingTemplate.convertAndSend(mqTopic, message);
                }
            });
        }
    }

    /**
     * 点对点模式,发布订阅模式没配置containerFactory不能用
     * @param queueName
     * @param message
     */
    @Override
    public void sendQueue(String queueName, String message) {
        if (!StringUtils.isEmpty(message)) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    Destination queue = new ActiveMQQueue(queueName);
                    jmsMessagingTemplate.convertAndSend(queue, message);
                }
            });
        }
    }

    /*@Override
    public void send(Topic topic, String msg) {
        try {
            //创建连接工厂
            ActiveMQConnectionFactory connFactory = new ActiveMQConnectionFactory(
                    "admin",
                    "admin",
                    "tcp://localhost:61616");
            connFactory.setMaxThreadPoolSize(1);

            //连接到JMS提供者
            Connection conn = connFactory.createConnection();
//            conn.setClientID("producer1");
            conn.start();

            //事务性会话，自动确认消息
            Session session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //消息的目的地
            Destination destination = session.createTopic(topic.getKey());
            //消息生产者
            MessageProducer producer = session.createProducer(destination);
//            producer.setDeliveryMode(DeliveryMode.PERSISTENT); //持久化

           //文本消息
          TextMessage textMessage = session.createTextMessage("这是文本消息");
          producer.send(textMessage);

            //键值对消息
           *//* MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("reqDesc", msg);
            producer.send(mapMessage);*//*
//
//            //流消息
//            StreamMessage streamMessage = session.createStreamMessage();
//            streamMessage.writeString("这是流消息");
//            producer.send(streamMessage);
//
//            //字节消息
//            String s = "BytesMessage字节消息";
//            BytesMessage bytesMessage = session.createBytesMessage();
//            bytesMessage.writeBytes(s.getBytes());
//            producer.send(bytesMessage);
//
//            //对象消息
//            User user = new User("obj_info", "对象消息"); //User对象必须实现Serializable接口
//            ObjectMessage objectMessage = session.createObjectMessage();
//            objectMessage.setObject(user);
//            producer.send(objectMessage);


            session.commit(); //提交会话，该条消息会进入"queue"队列，生产者也完成了历史使命
            producer.close();
            session.close();
            conn.close();
            //在事务性会话中，只有commit之后，消息才会真正到达目的地

        } catch (Exception e) {
            e.printStackTrace();

        }

    }*/
}
