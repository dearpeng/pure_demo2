package com.alimama.web.controller;

import com.alimama.api.enums.TopicEnum;
import com.alimama.api.model.Employee;
import com.alimama.api.service.IEmployeeService;
import com.alimama.api.service.IMqMessageProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by PengWX on 2019/4/23.
 */
@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IMqMessageProducerService mqMessageProducerService;


    @ResponseBody
    @RequestMapping("list")
    public List<Employee> getEmployeeList(){
        return employeeService.getAllEmployee();
    }

    @RequestMapping("test")
    @ResponseBody
    public String test(){
        return "hello";
    }
    @ResponseBody
    @RequestMapping("publish")
    public void publishMessage(String msg){
        mqMessageProducerService.send(TopicEnum.LOAN, msg);
    }
    @ResponseBody
    @RequestMapping("publishold")
    public void publishMessageOld(String msg){
        mqMessageProducerService.allSend (TopicEnum.LOAN, msg);
    }
    @ResponseBody
    @RequestMapping("publishqueue")
    public void publishqueue(String msg){
        mqMessageProducerService.sendQueue("cheguo.queues.loan.test", msg);
    }
}
