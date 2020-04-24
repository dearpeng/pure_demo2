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
import java.io.*;

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
    public String setSession(HttpSession session) throws Exception {
        throw new Exception("controller异常!");
       /* session.setAttribute("123456", "zhangsan");
        return port;*/
    }

    @GetMapping("getsession")
    @ResponseBody
    public String getsession(HttpSession session) {
        return session.getAttribute("123456") + "port";
    }

    @RequestMapping("testKafka")
    @ResponseBody
    public void testKafka(String message) {
        kafkaProducerService.sendTopicMessage(TopicEnum.LOAN, message);
        //使用 try with resource
        try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(new File("test.txt")));
             BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(new File("out.txt")))) {
            int b;
            while ((b = bin.read()) != -1) {
                bout.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //使用try catch finally
        BufferedInputStream bin = null;
        BufferedOutputStream bout = null;
        try {
            bin = new BufferedInputStream(new FileInputStream(new File("test.txt")));
            bout = new BufferedOutputStream(new FileOutputStream(new File("out.txt")));
            int b;
            while ((b = bin.read()) != -1) {
                bout.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bin != null) {
                try {
                    bin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bout != null) {
                        try {
                            bout.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }


      /*  try {
            Connection e = new Connection();
            Throwable var2 = null;
            try {
                e.sendData();
            } catch (Throwable var12) {
                var2 = var12;
                throw var12;
            } finally {
                if (e != null) {
                    if (var2 != null) {
                        try {
                            e.close();
                        } catch (Throwable var11) {
                            var2.addSuppressed(var11);
                        }
                    } else {
                        e.close();
                    }
                }
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }*/


    }
}
