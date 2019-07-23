package com.alimama.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@ImportResource(locations = {"classpath*:/spring/dubbo-context.xml"})
//加入@EnableJms注解就是异步，没有加 @EnableJms注解则默认是同步。
@EnableJms
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
