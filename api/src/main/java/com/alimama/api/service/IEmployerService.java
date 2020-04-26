package com.alimama.api.service;

import com.alimama.api.model.Employee;
import com.alimama.api.model.EmployeeExample;
import com.alimama.api.model.Employer;
import com.alimama.api.model.EmployerExample;

import java.util.List;

/**
 * Created by PengWX on 2019/4/23.
 */
public interface IEmployerService {

    List<Employer> selectByRealName(String username);
}
