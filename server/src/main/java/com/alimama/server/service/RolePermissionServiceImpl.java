package com.alimama.server.service;

import com.alimama.api.model.Permission;
import com.alimama.api.model.RolePermission;
import com.alimama.api.model.RolePermissionExample;
import com.alimama.api.service.IPermissionService;
import com.alimama.api.service.IRolePermissionService;
import com.alimama.server.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by PengWX on 2018/12/24.
 */
@Service(value = "rolePermissionService")
public class RolePermissionServiceImpl implements IRolePermissionService {
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<RolePermission> selectRolePermissionByRoleIds(List<Long> roleIds) {

        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        RolePermissionExample.Criteria criteria = rolePermissionExample.or();
        criteria.andRoleIdIn(roleIds);
        return rolePermissionMapper.selectByExample(rolePermissionExample);
    }
}
