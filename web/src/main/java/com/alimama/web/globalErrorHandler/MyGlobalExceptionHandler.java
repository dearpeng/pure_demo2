package com.alimama.web.globalErrorHandler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
            jsonObject.put("msg", e.getMessage());
            e1.printStackTrace();
        } catch (Exception e1) {
            jsonObject.put("code", 30000);
            jsonObject.put("msg", e.getMessage());
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
}
