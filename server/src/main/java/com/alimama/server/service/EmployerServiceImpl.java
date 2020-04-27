package com.alimama.server.service;

import com.alimama.api.model.*;
import com.alimama.api.modelVOs.EmployerVO;
import com.alimama.api.service.*;
import com.alimama.server.mapper.EmployerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by PengWX on 2019/4/23.
 */
@Service("employerService")
public class EmployerServiceImpl implements IEmployerService {

    @Autowired
    private EmployerMapper employerMapper;

    @Autowired
    private IEmployerRoleService employerRoleService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IRolePermissionService rolePermissionService;

    @Override
    public List<Employer> selectByRealName(String username) {
        EmployerExample example = new EmployerExample();
        example.or().andRealNameEqualTo(username);
        return employerMapper.selectByExample(example);
    }

    @Override
    public List<Employer> selectByMobile(String username) {
        EmployerExample example = new EmployerExample();
        example.or().andMobilePhoneEqualTo(username);
        return employerMapper.selectByExample(example);
    }

    @Override
    public EmployerVO selectEmployerAndPermissions(String mobile) {
        EmployerVO employerVO = new EmployerVO();
        EmployerExample example = new EmployerExample();
        example.or().andMobilePhoneEqualTo(mobile);
        List<Employer> employers = employerMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(employers)) {
            List<EmployerRole> empRoles = employerRoleService.selectRoleByEmployerId(employers.get(0).getId());
            if (!CollectionUtils.isEmpty(empRoles)) {
                List<Long> roleIds = empRoles.stream().map(EmployerRole::getRoleId).collect(Collectors.toList());
                List<Role> roles = roleService.selectRolesByRoleIds(roleIds);
                employerVO.setRoles(new HashSet<>(roles));
                List<RolePermission> rolePermissions = rolePermissionService.selectRolePermissionByRoleIds(roleIds);
                if (!CollectionUtils.isEmpty(rolePermissions)) {
                    List<Long> permissionIds = rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());

                    List<Permission> permissions = permissionService.selectPermissionsByPermissionId(permissionIds);
                    HashSet<Permission> hashSet = new HashSet<>(permissions);
                    employerVO.setPermissions(hashSet);
                }
            }
        }
        return employerVO;
    }
}
