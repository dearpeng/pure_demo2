package com.alimama.web.controller;

import com.alibaba.fastjson.JSON;
import com.alimama.api.model.Department;
import com.alimama.api.model.Employee;
import com.alimama.api.model.EmployeeExample;
import com.alimama.api.service.IDepartmentService;
import com.alimama.api.service.IEmployeeService;
import com.alimama.api.utils.WebUtil;
import com.alimama.web.globalErrorHandler.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * Created by PengWX on 2019/4/23.
 */
@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;

    private final static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping("list")
    public String getEmployeeList(Model model) throws Exception{
//        throw new Exception("测试查询异常全局捕获!");
        logger.info("查询所有数据");
        List<Employee> allEmployees = null;
        allEmployees = employeeService.getAllEmployee();
        model.addAttribute("emps", allEmployees);
        return "list";
    }

    @RequestMapping("test")
    @ResponseBody
    public Integer test() {
        Integer num = 1 / 0;
        return num;
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

    /**
     * 删除emp
     * @param id
     * @return
     */
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id")Long id){
        employeeService.deleteById(id);
        return "redirect:/employee/list";
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @RequestMapping("/emp")
    public String addEmp(Employee employee){
        employeeService.addEmployee(employee);
        //重定向到emps请求  / 表示当前地址
        return "redirect:/employee/list";
    }

    /**
     * 获取部门,点击添加员工,跳转页面
     * @param model
     * @return
     */
    @GetMapping("/emp")
    public String toAddPage(Model model){
        List<Department> departments = departmentService.getAllDepartmentList();
        model.addAttribute("depts", departments);
        return "add";
    }
}
