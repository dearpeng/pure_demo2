package com.alimama.server.service;

import com.alimama.api.model.Employer;
import com.alimama.api.model.EmployerExample;
import com.alimama.api.service.IEmployerService;
import com.alimama.server.mapper.EmployerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PengWX on 2019/4/23.
 */
@Service("employerService")
public class EmployerServiceImpl implements IEmployerService {

    @Autowired
    private EmployerMapper employerMapper;


    @Override
    public List<Employer> selectByRealName(String username) {
        EmployerExample example = new EmployerExample();
        example.or().andRealNameEqualTo(username);
        return employerMapper.selectByExample(example);
    }
}
