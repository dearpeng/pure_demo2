package com.alimama.api.dataConverts;


import com.alimama.api.model.Employee;
import com.alimama.api.model.EmployeeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Created by PengWX on 2019/8/29.
 */
@Mapper
public interface IEmployeeDataConverter {

    IEmployeeDataConverter INSTANCE = Mappers.getMapper(IEmployeeDataConverter.class);


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