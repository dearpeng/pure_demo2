package com.alimama.web.controller;

import com.alimama.api.enums.TopicEnum;
import com.alimama.api.service.IKafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by PengWX on 2019/6/12.
 */
@Controller
public class HelloController {
    @Value("server.port")
    private String port;

    @Autowired
    private IKafkaProducerService kafkaProducerService;

    @GetMapping("setsession")
    @ResponseBody
    public String setSession(HttpSession session) {
        session.setAttribute("123456", "zhangsan");
        return port;
    }

    @GetMapping("getsession")
    @ResponseBody
    public String getsession(HttpSession session) {
        return session.getAttribute("123456") + "port";
    }

    @RequestMapping("testKafka")
    @ResponseBody
    public void testKafka(String message){
        kafkaProducerService.sendTopicMessage(TopicEnum.LOAN,message);
    }
}
