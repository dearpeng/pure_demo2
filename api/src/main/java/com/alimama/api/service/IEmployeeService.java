package com.alimama.api.service;

import com.alimama.api.model.Employee;
import com.alimama.api.model.EmployeeExample;

import java.util.List;

/**
 * Created by PengWX on 2019/4/23.
 */
public interface IEmployeeService {
    List<Employee> getAllEmployee() throws Exception;

    Integer addEmployee(Employee employee);

    List<Employee> selectByExample(EmployeeExample example);

    Integer deleteById(Long id);
}
