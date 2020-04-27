package com.alimama.api.service;

import com.alimama.api.model.Employer;
import com.alimama.api.model.Permission;

import java.util.List;

/**
 * Created by PengWX on 2019/4/23.
 */
public interface IPermissionService {

    List<Permission> selectPermissionsByPermissionId(List<Long> permissionIds);
}
