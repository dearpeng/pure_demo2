package com.alimama.server.config;

import com.alimama.server.interceptor.MybatisResultInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * 自定义mybatis拦截器
 * Created by PengWX on 2019/12/3.
 */
@Configuration
public class MybatisConfig {
    @Resource
    private MybatisResultInterceptor mybatisResultInterceptor;

    @Bean
    public String myInterceptor()
    {
        Properties properties = new Properties();
        this.mybatisResultInterceptor.setProperties(properties);
        return "interceptor";
    }
}
