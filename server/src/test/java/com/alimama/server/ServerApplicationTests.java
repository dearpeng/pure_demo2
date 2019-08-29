package com.alimama.server;

import com.alibaba.fastjson.JSON;
import com.alimama.api.model.Employee;
import com.alimama.api.model.EmployeeVo;
import com.alimama.api.service.IEmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {

    @Test
    public void contextLoads() {
            Employee employee = new Employee();
            employee.setId(1l);
            employee.setLastName("张三");
            employee.setBirth(new Date());
            employee.setEmail("123123@qq.com");
            employee.setDepartmentId(222l);
            employee.setDepartmentName("技术部");
            employee.setPassword("6666666");
            employee.setGender(new Byte("0"));
            employee.setSalt("111111111111");
            EmployeeVo employeeVo = IEmployeeMapper.INSTANCE.employee2EmployeeVo(employee);
            System.out.println(JSON.toJSONString(employeeVo));
    }



}
