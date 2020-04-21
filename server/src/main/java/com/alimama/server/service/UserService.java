package com.alimama.server.service;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alimama.api.model.Employee;
import com.alimama.server.utils.PdfUtils;
import com.itextpdf.text.DocumentException;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 自己的测试
 * Created by PengWX on 2020/4/15.
 */
public class UserService {


    // 定义的资源
    public static final String USER_RES = "userResource";
    /**
     * 根据uid获取用户信息
     * @param uid uid
     * @return 用户信息
     */
    /*@SentinelResource(value = USER_RES, blockHandler = "exceptionHandler")
    public static User getUser(Long uid){
        // 业务代码
        User user = new User();
        user.setUid(uid);
        user.setName("user-" + uid);
        System.out.println("车门焊丝,开车了!!!");
        return user;
    }*/

    public User exceptionHandler(Long epId, BlockException e) {
        System.out.println("========================================");
        System.out.println("限流了,怎么办???");
        System.out.println("========================================");
        return null;
    }

    public static class User {
        private Long uid;
        private String name;

        public Long getUid() {
            return uid;
        }

        public void setUid(Long uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static User getUser(Long uid){
        Entry entry = null;
        try {
            // 流控代码
            entry = SphU.entry(USER_RES);
            // 业务代码
            User user = new User();
            user.setUid(uid);
            user.setName("user-" + uid);
            System.out.println("车门焊丝,开车了!!!");
            return user;
        }catch(BlockException e){
            // 被限流了
            System.out.println("被限流了!!!");
            System.out.println("[getUser] has been protected! Time="+System.currentTimeMillis());
        }finally {
            if(entry!=null){
                entry.exit();
            }
        }
        return null;
    }


    /**
     * 测试sentinel限流
     * @param args
     */
    /*public static void main(String[] args) {
        initFlowQpsRule();
        for (int i=0;i<100;i++) {
            UserService.getUser(100L);
        }
    }*/


    /**
     * 测试打印jvm内存信息
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("max memory:"+Runtime.getRuntime().maxMemory());
        System.out.println("free memory:"+Runtime.getRuntime().freeMemory());
        System.out.println("total memory:"+Runtime.getRuntime().totalMemory());
        System.out.println("=========================================");

        Byte[] bytes = new Byte[1 * 1024 * 1024];
        System.out.println("max memory:"+Runtime.getRuntime().maxMemory());
        System.out.println("free memory:"+Runtime.getRuntime().freeMemory());
        System.out.println("total memory:"+Runtime.getRuntime().totalMemory());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        Byte[] bytes2 = new Byte[1 * 1024 * 1024];
        System.out.println("max memory:"+Runtime.getRuntime().maxMemory());
        System.out.println("free memory:"+Runtime.getRuntime().freeMemory());
        System.out.println("total memory:"+Runtime.getRuntime().totalMemory());
        System.out.println("##################################################");


        try {
            PdfUtils.createPdf("C:\\Users\\ftcs\\Desktop\\generatecode\\测试文档.pdf","Hello World! Hello People! " +
                    "Hello Sky! Hello Sun! Hello Moon! Hello Stars!");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void initFlowQpsRule() {
        List<FlowRule> rules = new ArrayList<FlowRule>();
        FlowRule rule1 = new FlowRule();
        rule1.setResource(USER_RES);
        // set limit qps to 20
        rule1.setCount(2);
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setLimitApp("default");
        rules.add(rule1);
        FlowRuleManager.loadRules(rules);
    }
}
