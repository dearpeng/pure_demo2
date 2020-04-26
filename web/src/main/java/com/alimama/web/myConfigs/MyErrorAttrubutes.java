package com.alimama.web.myConfigs;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * springboot只有规定的几个错误返回数据,这边需要定制一些自己的错误信息
 * Created by jx on 2019/2/24.
 */
@Component
public class MyErrorAttrubutes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("company", "alibaba");
        //ext是我们自定义异常处理器中的错误
        map.put("ext", webRequest.getAttribute("ext", 0));
        return map;
    }
}
