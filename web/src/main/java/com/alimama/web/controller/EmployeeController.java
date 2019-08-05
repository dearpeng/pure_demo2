package com.alimama.web.controller;

import com.alimama.api.enums.TopicEnum;
import com.alimama.api.model.Employee;
import com.alimama.api.service.IEmployeeService;
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

}
