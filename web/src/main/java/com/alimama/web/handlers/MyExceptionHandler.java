package com.alimama.web.handlers;

import com.alimama.web.selfExceptions.UserNotExistExcception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常处理
 * 不想只是使用spring给出的异常返回的json数据,还想自己定义一些json数据
 * Created by jx on 2019/2/17.
 */
//成为异常处理器加注解
@ControllerAdvice
public class MyExceptionHandler {
    /**
     * 浏览器和postman都是返回json数据
     * @param e
     * @return
     */
   /* @ExceptionHandler(UserNotExistExcception.class)
    @ResponseBody
    public Map<String,Object> handlerException(Exception e){
        Map<String, Object> map = new HashMap<>();
        map.put("code", "用户在哪里啊");
        map.put("message", e.getMessage());
        return map;
    }*/

    /**
     * 单独这么写,这边的code和message取不到
     * @param e
     * @return
     */
    @ExceptionHandler(UserNotExistExcception.class)
    public String handlerException(Exception e, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        /**
         * Integer statusCode = (Integer) request
         * 				.getAttribute("javax.servlet.error.status_code");
         */
        request.setAttribute("javax.servlet.error.status_code", 500);
        map.put("code", "用户在哪里啊");
        map.put("message", e.getMessage());
        request.setAttribute("ext",map);
        //转发给basicErrorController,它会帮助我们如果是浏览器展示的是错误页面,如果是postman返回的是json数据
        return "forward:/error";
    }
}
