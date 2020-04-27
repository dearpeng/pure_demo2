package com.alimama.server.service;

import com.alimama.api.model.Department;
import com.alimama.api.model.DepartmentExample;
import com.alimama.api.model.Permission;
import com.alimama.api.model.PermissionExample;
import com.alimama.api.service.IDepartmentService;
import com.alimama.api.service.IPermissionService;
import com.alimama.server.mapper.DepartmentMapper;
import com.alimama.server.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by PengWX on 2018/12/24.
 */
@Service(value = "permissionService")
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> selectPermissionsByPermissionId(List<Long> permissionIds) {

        PermissionExample permissionExample = new PermissionExample();
        PermissionExample.Criteria criteria = permissionExample.createCriteria();
        criteria.andIdIn(permissionIds);
        return permissionMapper.selectByExample(permissionExample);
    }
}
