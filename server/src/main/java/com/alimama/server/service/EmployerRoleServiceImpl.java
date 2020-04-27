package com.alimama.server.service;

import com.alimama.api.model.EmployerRole;
import com.alimama.api.model.EmployerRoleExample;
import com.alimama.api.service.IEmployerRoleService;
import com.alimama.server.mapper.EmployerRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by PengWX on 2019/4/23.
 */
@Service("employerRoleService")
public class EmployerRoleServiceImpl implements IEmployerRoleService {

    @Autowired
    private EmployerRoleMapper employerRoleMapper;

    @Override
    public List<EmployerRole> selectRoleByEmployerId(Long id) {

        EmployerRoleExample example = new EmployerRoleExample();
        EmployerRoleExample.Criteria criteria = example.or();
        criteria.andEmployerIdEqualTo(id);
        return employerRoleMapper.selectByExample(example);
    }
}
