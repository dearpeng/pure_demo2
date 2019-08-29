package com.alimama.api.service;


import com.alimama.api.model.Employee;
import com.alimama.api.model.EmployeeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import javax.xml.transform.Source;

/**
 * Created by PengWX on 2019/8/29.
 */
@Mapper
public interface IEmployeeMapper {

    IEmployeeMapper INSTANCE = Mappers.getMapper(IEmployeeMapper.class);


    @Mappings(
            {
                    @Mapping(source = "lastName", target = "realName"),
                    @Mapping(source = "departmentId", target = "depId"),
                    @Mapping(source = "departmentName", target = "depName"),
                    @Mapping(source = "password", target = "pwd")
            }
    )
    EmployeeVo employee2EmployeeVo(Employee employee);


}