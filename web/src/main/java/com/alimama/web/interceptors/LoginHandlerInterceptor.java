package com.alimama.web.interceptors;

import com.alimama.api.model.Employee;
import com.alimama.api.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Created by xj on 2018/11/29.
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    private IEmployeeService employeeService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*HandlerMethod handlerMethod = (HandlerMethod) handler;
//        handlerMethod.getMethodAnnotation(UnAuthRequest.class);
        Method method = handlerMethod.getMethod();
        UnAuthRequest annotation = method.getAnnotation(UnAuthRequest.class);
        if (Objects.nonNull(annotation)) {
            return true;
        }*/
        Employee employee  = (Employee) request.getSession().getAttribute("user");
        if (Objects.nonNull(employee)) {
            return true;
        }else {
            request.setAttribute("msg","该用户不存在");
//            response.sendRedirect(request.getContextPath()+"/index.html");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
