package com.alimama.web.controller;

import com.alimama.api.enums.TopicEnum;
import com.alimama.api.model.Employee;
import com.alimama.api.service.IEmployeeService;
import com.alimama.web.globalErrorHandler.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @ResponseBody
    @RequestMapping("list")
    public List<Employee> getEmployeeList(Long epId) {
        logger.info("查询所有数据");
        return employeeService.getAllEmployee(epId);
    }

    @RequestMapping("test")
    @ResponseBody
    public Integer test() {
        Integer num = 1 / 0;
        return num;
    }

    @RequestMapping("testHanlderBiz")
    @ResponseBody
    public Integer testHanlderBiz() {
        Integer num = null;
        try {
            num = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return num;
    }


}
