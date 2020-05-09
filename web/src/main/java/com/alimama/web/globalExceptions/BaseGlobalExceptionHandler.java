package com.alimama.web.globalExceptions;


import com.alimama.api.utils.WebUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常处理基类
 */


@ControllerAdvice
public class BaseGlobalExceptionHandler {


  /*  @ExceptionHandler(value =
            {
                    Exception.class
            }
    )
    @ResponseBody
    public String handleBizException(Exception e) {
        return WebUtil.getFailureJson(e.getMessage());
    }*/


    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseBody
    public String handleUnAuthenticationException(AuthenticationException ex) {
        return WebUtil.getFailureJson(ex.getMessage());
    }
    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleAuthorizationExceptionException(AuthorizationException e) {
        return WebUtil.getFailureJson(e.getMessage());
    }
}
