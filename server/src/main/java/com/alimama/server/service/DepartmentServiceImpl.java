package com.alimama.server.service;

import com.alimama.api.model.Department;
import com.alimama.api.model.DepartmentExample;
import com.alimama.api.service.IDepartmentService;
import com.alimama.server.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by PengWX on 2018/12/24.
 */
@Service(value = "departmentService")
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public List<Department> getAllDepartmentList() {
        return departmentMapper.selectByExample(new DepartmentExample());
    }
}
