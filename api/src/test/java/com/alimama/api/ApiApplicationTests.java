package com.alimama.api;

import com.alibaba.fastjson.JSON;
import com.alimama.api.model.Employee;
import com.alimama.api.model.EmployeeVo;
import com.alimama.api.service.IEmployeeMapper;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class ApiApplicationTests {

    @Test
    public void contextLoads() {
    }
}
