package com.alimama.web.adapters;


import com.alimama.web.interceptors.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jx on 2018/11/18.
 */
@Configuration
public class MyConfigurerAdapter extends WebMvcConfigurerAdapter {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("").excludePathPatterns("/index","/index.html","/","/user/login","/signOut");
        super.addInterceptors(registry);
    }

    //所有的WebMvcConfigurerAdapter组件都会一起起作用，所以自己定义一个adapter
    @Bean //将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            //这边写就不用单独写controller转到登陆页面
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/index.html").setViewName("index");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
            /*@Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("*").excludePathPatterns("/index","/","/user/login");
                super.addInterceptors(registry);
            }*/

        };

        return adapter;
    }

   /* @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static*//**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }*/

    /* *
     * 将login请求隐射到index页面
     * @author pengWX
     * @param @param registry:
     * @return void
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

}
