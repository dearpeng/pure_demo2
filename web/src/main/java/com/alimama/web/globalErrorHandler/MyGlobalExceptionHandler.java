package com.alimama.web.globalErrorHandler;

import com.alibaba.fastjson.JSONObject;
import com.alimama.api.utils.WebUtil;
import com.alimama.web.selfExceptions.UserNotExistExcception;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by PengWX on 2019/9/23.
 * 全局捕获异常类，所有@RequestMapping上，所有的异常都会被捕获
 */
@ResponseBody
@ControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> errorHandle(Exception e) {

        //可以使用try-catch方式也可以使用 if (e instanceof BizException)方式,也可以每种异常写一个@ExceptionHandler
        JSONObject jsonObject = new JSONObject();
        try {
            throw e;
        } catch (BizException e1) {
            jsonObject.put("code", 20000);
            jsonObject.put("message", /*e.getMessage()*/"出20000错误了");
            e1.printStackTrace();
        } catch (Exception e1) {
            jsonObject.put("code", 30000);
            jsonObject.put("message", e.getMessage());
            e1.printStackTrace();
        }
        return jsonObject;
    }


    /**
     * 自己定义的exception处理,有具体的xxxException会先使用具体的handler,不然就是使用ExceptionHandler
     *
     * @param e
     * @return
     */
   /* @ExceptionHandler(value = BizException.class)
    public Map<String, Object> bizExceptionHandle(BizException e) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("msg", e.getMessage());
        return jsonObject;
    }*/


    @ExceptionHandler(value = AuthenticationException.class)
    public String handleUnAuthenticationException(AuthenticationException ex) {
        return WebUtil.getFailureJson("没有验证!");
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleAuthorizationExceptionException(AuthorizationException e) {

        return WebUtil.getFailureJson("没有授权!");
    }

    /**
     * 单独这么写,这边的code和message取不到
     *  * 自定义异常处理
     *  * 不想只是使用spring给出的异常返回的json数据,还想自己定义一些json数据
     * @param e
     * @return
     */
   /* @ExceptionHandler(UserNotExistExcception.class)
    public String handlerException(Exception e, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        *//**
         * Integer statusCode = (Integer) request
         * 				.getAttribute("javax.servlet.error.status_code");
         *//*
        request.setAttribute("javax.servlet.error.status_code", 500);
        map.put("code", "用户在哪里啊");
        map.put("message", e.getMessage());
        request.setAttribute("ext",map);
        //转发给basicErrorController,它会帮助我们如果是浏览器展示的是错误页面,如果是postman返回的是json数据
        return "forward:/error";
    }*/

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //执行之前设置时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
