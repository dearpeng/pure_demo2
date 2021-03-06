package com.alimama.server;

import com.alibaba.fastjson.JSON;
import com.alimama.api.dataConverts.IEmployeeDataConverter;
import com.alimama.api.model.Employee;
import com.alimama.api.model.EmployeeVo;
import com.alimama.api.myDataPage.DataPage;
import com.alimama.api.service.IEmployeeService;
import com.alimama.server.chains.CommandChain;
import com.alimama.server.service.UserService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ContextBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {

    @Autowired
    private IEmployeeService employeeService;

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
        EmployeeVo employeeVo = IEmployeeDataConverter.INSTANCE.employee2EmployeeVo(employee);
        System.out.println(JSON.toJSONString(employeeVo));
    }

    @Test
    public void getEmployee() {
        // 测试使用javax validation

        Employee employee = new Employee();
        employee.setId(null);
        employee.setLastName(null);
        employee.setBirth(new Date());
        employee.setEmail("123123@qq.com");
        employee.setDepartmentId(222L);
        employee.setDepartmentName("技术部");
        employee.setPassword("6666666");
        employee.setGender(new Byte("0"));
        employee.setSalt("111111111111");

        employeeService.addEmployee(employee);
    }


    @Test
    public void sentinelTest() {
        UserService.initFlowQpsRule();
        for (int i = 0; i < 100; i++) {
            try {
                PageInfo<Employee> allEmployee = employeeService.getAllEmployee(1,10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(JSON.toJSONString("******************************"));
    }

    @Test
    public void test() throws Exception {
        CommandChain commandChain = new CommandChain();
        Context context = new ContextBase();
        context.put("k1", 1);
        commandChain.execute(context);
    }


}
