package com.alimama.api.service;

import com.alimama.api.model.Employee;
import com.alimama.api.model.EmployeeExample;
import com.alimama.api.myDataPage.DataPage;
import com.alimama.api.utils.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by PengWX on 2019/4/23.
 */
public interface IEmployeeService {
    PageInfo<Employee> getAllEmployee(Integer page, Integer limit) throws Exception;

    Integer addEmployee(Employee employee);

    List<Employee> selectByExample(EmployeeExample example);

    Integer deleteById(Long id);

    List<Employee> getAllEmployees(Integer page, Integer limit);

    Employee getEmployeeDetails(Long id);

    Long updateEmployee(Employee employee);

    Integer batchDeleteEmp(String ids);
}
