package com.alimama.api.service;

import com.alimama.api.model.EmployerRole;

import java.util.List;

/**
 * Created by PengWX on 2019/4/23.
 */
public interface IEmployerRoleService {

    List<EmployerRole> selectRoleByEmployerId(Long id);
}
