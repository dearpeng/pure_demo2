package com.alimama.web.controller;

import com.alimama.api.model.*;
import com.alimama.api.service.IDepartmentService;
import com.alimama.api.service.IEmployeeService;
import com.alimama.api.service.IEmployerService;
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
@RequestMapping(value = "/employer")
public class EmployerController {
    @Autowired
    private IEmployerService employerService;

    private final static Logger logger = LoggerFactory.getLogger(EmployerController.class);


    @PostMapping(value ="/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session){

        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)){

            List<Employer> emps = employerService.selectByRealName(username);
            if (CollectionUtils.isEmpty(emps)) {
                model.addAttribute("msg", "用户名不存在");
                return "index.html";
            }else {
                Employer emp = emps.get(0);
                if (Objects.isNull(emp)){
                    model.addAttribute("msg", "用户名或密码为空或错误");
                    return "index.html";
                }else{
                    session.setAttribute("user",emp);
                    return "redirect:/main.html";
                }
                /*String md5Hex = MD5.md5Hex(password, emp.getSalt());
                if (Objects.equals(md5Hex, emp.getPassword())) {
                    //防止表单重复提交,采用重定向
                    session.setAttribute("user",emp);
                    return "redirect:/main.html";
                }else {
                    model.addAttribute("msg", "用户名或密码为空或错误");
                    return "index.html";
                }*/
            }
        }else {
            model.addAttribute("msg", "用户名或密码为空");
            return "index.html";
        }
    }

}
