package com.alimama.server.service;

import com.alimama.api.model.Role;
import com.alimama.api.model.RoleExample;
import com.alimama.api.service.IRoleService;
import com.alimama.server.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by PengWX on 2019/4/23.
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectRolesByRoleIds(List<Long> roleIds) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andIdIn(roleIds);
        return roleMapper.selectByExample(roleExample);
    }
}
