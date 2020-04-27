package com.alimama.web.controller;

import com.alimama.api.model.*;
import com.alimama.api.service.IDepartmentService;
import com.alimama.api.service.IEmployeeService;
import com.alimama.api.service.IEmployerService;
import com.alimama.api.utils.WebUtil;
import com.alimama.web.globalErrorHandler.BizException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
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


    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session) {

        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            //获取当前执行的用户：
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            try {
                //进行验证，这里可以捕获异常，然后返回对应信息
                subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
            } catch (AuthenticationException e) {
                model.addAttribute("msg", "用户名或密码为空或错误");
                return "index.html";
            } catch (AuthorizationException e) {
                model.addAttribute("msg", "没有权限");
                return "index.html";
            }
            List<Employer> emps = employerService.selectByMobile(username);
            session.setAttribute("user", emps.get(0));
            return "redirect:/main.html";

            /*List<Employer> emps = employerService.selectByMobile(username);
            if (CollectionUtils.isEmpty(emps)) {
                model.addAttribute("msg", "用户名不存在");
                return "index.html";
            } else {
                Employer emp = emps.get(0);
                if (Objects.isNull(emp)) {
                    model.addAttribute("msg", "用户名或密码为空或错误");
                    return "index.html";
                } else {
                    session.setAttribute("user", emp);
                    return "redirect:/main.html";
                }
//                String md5Hex = MD5.md5Hex(password, emp.getSalt());
//                if (Objects.equals(md5Hex, emp.getPassword())) {
//                    //防止表单重复提交,采用重定向
//                    session.setAttribute("user",emp);
//                    return "redirect:/main.html";
//                }else {
//                    model.addAttribute("msg", "用户名或密码为空或错误");
//                    return "index.html";
//                }
            }*/
        } else {
            model.addAttribute("msg", "用户名或密码为空");
            return "index.html";
        }
    }

}
