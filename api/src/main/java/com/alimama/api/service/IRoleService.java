package com.alimama.api.service;

import com.alimama.api.model.Role;

import java.util.List;

/**
 * Created by PengWX on 2019/4/23.
 */
public interface IRoleService {

    List<Role> selectRolesByRoleIds(List<Long> roleIds);
}
