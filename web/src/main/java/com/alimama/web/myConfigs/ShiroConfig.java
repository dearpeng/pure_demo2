package com.alimama.web.myConfigs;

import com.alimama.web.realms.EmployerRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by PengWX on 2020/4/27.
 */
@Configuration
public class ShiroConfig {
    //不加这个注解不生效，具体不详
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    //将自己的验证方式加入容器
    @Bean
    public EmployerRealm employerRealm() {
        EmployerRealm employerRealm = new EmployerRealm();
        return employerRealm;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(employerRealm());
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<>();
        //登出
        map.put("/employer/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        map.put("/static/**", "anon");
        map.put("/employer/user/login", "anon");
        map.put("/**", "authc");

        //首页
        shiroFilterFactoryBean.setSuccessUrl("/index.html");
        //跳转登录
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        //错误页面，认证不通过跳转错误页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        //对所有用户认证
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //在两个独立的和Web应用程序，你可能需要使用Shiro的注解安全检查（例如@RequiresRoles，@RequiresPermissions等这需要四郎的Spring AOP的集成扫描相应的注释类和执行安全逻辑是必要的。
    /* spring的配置
    <bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator" depends-on="lifecycleBeanPostProcessor"/>
         <bean class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
         <property name="securityManager" ref="securityManager"/>
    </bean>
    * */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
