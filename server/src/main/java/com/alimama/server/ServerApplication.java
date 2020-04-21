package com.alimama.server;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ImportResource(locations = {"classpath*:spring/application.xml"})
//启用注解
@EnableCaching
//加入@EnableJms注解就是异步，没有加 @EnableJms注解则默认是同步。
public class ServerApplication {

    public static void main(String[] args) {
        System.out.println("*****************服务提供开始启动**************");
        SpringApplication.run(ServerApplication.class, args);
       /* ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/application.xml");
        context.start();*/
        System.out.println("*****************服务提供启动完成*************");
    }

   /* @Configurationspring-boot-starter-data-redis
    @EnableDubbo(scanBasePackages = "com.alimama.server")
    @PropertySource("classpath:/application.properties")
    static public class ProviderConfiguration {

    }*/

    /**
     * sentinel初始化限流规则
     */
    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();

        FlowRule rule = new FlowRule();
        rule.setResource("getAllEmployee");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(1);
        rules.add(rule);

        rule = new FlowRule();
        rule.setResource("getAllEmployee1");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(1);
        rules.add(rule);

        FlowRuleManager.loadRules(rules);
    }

}
