package com.alimama.web.controller;

import com.alibaba.fastjson.JSON;
import com.alimama.api.enums.TopicEnum;
import com.alimama.api.model.Employee;
import com.alimama.api.service.IEmployeeService;
import com.alimama.api.utils.WebUtil;
import com.alimama.web.globalErrorHandler.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
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
    public String getEmployeeList(Long epId) {
        logger.info("查询所有数据");
        List<Employee> allEmployee = null;
        try {
            allEmployee = employeeService.getAllEmployee(epId);
        } catch (Exception e) {
            e.printStackTrace();
            return "查询客户列表失败!" + e.getMessage();
        }
        return JSON.toJSONString(allEmployee);
    }

    @RequestMapping("test")
    @ResponseBody
    public Integer test() {
        Integer num = 1 / 0;
        return num;
    }

    /**
     * validation异常捕获处理 （可能是MethodArgumentNotValidException或 BindException）
     *
     * @param e MethodArgumentNotValidException 异常
     * @return com.example.common.bean.JsonResult
     */
    @ExceptionHandler(value =  Exception.class)
    public String serviceHandle( Exception e) {
        return e.getLocalizedMessage()+e.getMessage();
    }

    @RequestMapping("addEmp")
    @ResponseBody
    public String addEmp(@Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> stringBuilder.append(objectError.getDefaultMessage()).append(";"));
            return   WebUtil.getFailureJson(stringBuilder.toString());
        }

        return WebUtil.getSuccessJson(employeeService.addEmployee(employee));

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
