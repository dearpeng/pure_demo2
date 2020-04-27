package com.alimama.api.service;

import com.alimama.api.model.Permission;
import com.alimama.api.model.RolePermission;

import java.util.List;

/**
 * Created by PengWX on 2019/4/23.
 */
public interface IRolePermissionService {

    List<RolePermission> selectRolePermissionByRoleIds(List<Long> roleIds);
}
