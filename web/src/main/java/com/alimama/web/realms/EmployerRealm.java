package com.alimama.web.realms;

import com.alimama.api.model.Employer;
import com.alimama.api.model.Permission;
import com.alimama.api.model.Role;
import com.alimama.api.modelVOs.EmployerVO;
import com.alimama.api.service.IEmployerService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.security.Permissions;
import java.util.List;
import java.util.Objects;

/**
 * shiro 验证,授权
 * Created by PengWX on 2020/4/27.
 */
public class EmployerRealm extends AuthorizingRealm {
    @Autowired
    private IEmployerService employerService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        Employer employer = (Employer) principalCollection.getPrimaryPrincipal();
        //根据用户名去数据库查询用户信息
       EmployerVO employerVO  = employerService.selectEmployerAndPermissions(employer.getMobilePhone());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (Objects.nonNull(employerVO)){

            //添加角色和权限
            for (Role role : employerVO.getRoles()) {
                //添加角色
                simpleAuthorizationInfo.addRole(role.getName());
                //添加权限
                for (Permission permission : employerVO.getPermissions()) {
                    simpleAuthorizationInfo.addStringPermission(permission.getPermissionName());
                }
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String mobile = authenticationToken.getPrincipal().toString();
        List<Employer> employers = employerService.selectByMobile(mobile);

        if (CollectionUtils.isEmpty(employers)) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            Employer employer = employers.get(0);
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(employer, employer.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
