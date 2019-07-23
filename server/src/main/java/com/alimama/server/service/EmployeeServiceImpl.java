package com.alimama.server.service;

import com.alimama.api.model.Employee;
import com.alimama.api.model.EmployeeExample;
import com.alimama.api.service.IEmployeeService;
import com.alimama.server.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by PengWX on 2019/4/23.
 */
@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    @Cacheable(value = "employeeListCache",keyGenerator = "keyGenerator")
    public List<Employee> getAllEmployee() {
        List<Employee> employees = employeeMapper.selectByExample(new EmployeeExample());
        return employees;
    }
}
