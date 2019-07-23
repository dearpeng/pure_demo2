package com.alimama.server.service;

import com.alibaba.fastjson.JSON;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.io.Serializable;

/**
 * mq消息接收,需要加上@Component
 * Created by PengWX on 2019/7/22.
 */
@Component
public class MqMessageComsumer {


    /*
     * 监听和读取消息
     */
    @JmsListener(destination = "cheguo.queues.loan.test",containerFactory = "jmsListenerContainerTopic")
    public void messageComsumer(TextMessage message) {
        try {
            System.out.println("===============================================");
            System.out.println("接受到1：" +  message.getText());
            System.out.println("===============================================");
            //客户端手动签收消息
            message.acknowledge();
            //TODO something
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    /*
     * 监听和读取消息,只接受queue消息
     */
    @JmsListener(destination = "cheguo.queues.loan.test",containerFactory = "jmsListenerContainerQueue")
    public void messageComsumer1(TextMessage message) {
        try {
            System.out.println("===============================================");
            System.out.println("接受到2：" + message.getText());
            System.out.println("===============================================");
            //客户端手动签收消息
            message.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        //TODO something
    }

    /*这种是理解activemq的写法*/
   /* @Override
    public void messageComsumer(Topic topic) {
        ConnectionFactory factory = new ActiveMQConnectionFactory(
                "admin",
                "admin",
                "tcp://localhost:61616"
        );

        try {
            Connection conn = factory.createConnection();
//        conn.setClientID("consumer1");
            conn.start();

            Session session = conn.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            //与生产者的消息目的地相同
            Destination dest = session.createTopic(topic.getKey());

            MessageConsumer messConsumer = session.createConsumer(dest);

            messConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        MapMessage m = (MapMessage)message;
                        System.out.println("consumer1接收到"+m.getString("reqDesc")+"的请求并开始处理，时间是"+new Date());
                        System.out.println("这里会停顿5s，模拟系统处理请求，时间是"+new Date());
                        Thread.sleep(5000);
                        System.out.println("consumer1接收到"+m.getString("reqDesc")+"的请求并处理完毕，时间是"+new Date());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }*/
}
