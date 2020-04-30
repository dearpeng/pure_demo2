package com.alimama.web.controller;

import com.alibaba.fastjson.JSON;
import com.alimama.api.model.Department;
import com.alimama.api.model.Employee;
import com.alimama.api.model.EmployeeExample;
import com.alimama.api.myDataPage.DataPage;
import com.alimama.api.service.IDepartmentService;
import com.alimama.api.service.IEmployeeService;
import com.alimama.api.utils.WebUtil;
import com.alimama.web.globalErrorHandler.BizException;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
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

    /**
     * 使用pageInfo分页
     *
     * @param page
     * @param limit
     * @return
     */
    /*@RequestMapping("list")
    @ResponseBody
    public String getEmployeeList(@RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit){
//        throw new Exception("测试查询异常全局捕获!");
        try {
            logger.info("查询所有数据");
            PageInfo<Employee> allEmployees = null;
            allEmployees = employeeService.getAllEmployee(page,limit);
            return WebUtil.getSuccessJson(0,allEmployees);
        } catch (Exception e) {
            return WebUtil.getFailureJson("获取列表失败!");
        }
    }*/
    @RequestMapping("pageList")
    @ResponseBody
    public String getEmployeeList(@RequestParam(value = "page") Integer page, @RequestParam(value = "limit") Integer limit) {
//        throw new Exception("测试查询异常全局捕获!");
        try {
            logger.info("查询所有数据");
            List<Employee> allEmployees = employeeService.getAllEmployees(page, limit);
            return WebUtil.getSuccessJson(0, allEmployees);
        } catch (Exception e) {
            return WebUtil.getFailureJson("获取列表失败!");
        }
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
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> stringBuilder.append(objectError.getDefaultMessage()).append(";"));
            return WebUtil.getFailureJson(stringBuilder.toString());
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

    /* *
     * 删除emp
     * @param id
     * @return
     * */
    @RequestMapping("/delete")
    @ResponseBody
    public String deleteEmp(@RequestParam(value = "id") Long id) {
        try {
            employeeService.deleteById(id);
        } catch (Exception e) {
            return WebUtil.getFailureJson(e.getMessage());
        }
        return WebUtil.getSuccessJson(id + "的客户已经删除!");
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
   /* @RequiresRoles("admin")
    @RequiresPermissions("add")
    @RequestMapping("/emp")
    @ResponseBody
    public String addEmp(Employee employee){
        employeeService.addEmployee(employee);
        //重定向到emps请求  / 表示当前地址
        return "redirect:/employee/list";
    }*/

    /**
     * 获取部门,点击添加员工,跳转页面
     *
     * @param model
     * @return
     */
    @GetMapping("/getAllDepartment")
    @ResponseBody
    public String toAddPage() {
        List<Department> departments = departmentService.getAllDepartmentList();
        return WebUtil.getSuccessJson(departments);
    }

    @RequiresRoles("admin")
    @RequiresPermissions("add")
    @RequestMapping("/update")
    @ResponseBody
    public String updateEmployee(Employee employee) {
        if (Objects.isNull(employee) || Objects.isNull(employee.getId())){
            return WebUtil.getFailureJson("主键为空!");
        }
       Long id =  employeeService.updateEmployee(employee);
        //重定向到emps请求  / 表示当前地址
        return WebUtil.getSuccessJson("更新成功!");
    }

    /**
     * 获取单个客户
     * @return
     */
    @RequestMapping("/getEmployee")
    @ResponseBody
    public String getEmployee(@RequestParam(value = "id")Long id) {
        Employee employee =  employeeService.getEmployeeDetails(id);
        return WebUtil.getSuccessJson(employee);
    }
}
