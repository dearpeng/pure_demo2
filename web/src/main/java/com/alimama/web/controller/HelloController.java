package com.alimama.web.controller;

import com.alimama.api.enums.TopicEnum;
import com.alimama.api.service.IMqMessageProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;

/**
 * Created by PengWX on 2019/6/12.
 */
public class HelloController {
    @Value("server.port")
    private String port;



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

}
