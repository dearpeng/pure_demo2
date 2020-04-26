package com.alimama.server.service;

import com.alimama.api.service.IRoleService;
import com.alimama.server.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by PengWX on 2019/4/23.
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

}
