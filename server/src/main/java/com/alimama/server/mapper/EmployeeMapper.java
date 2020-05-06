package com.alimama.server.mapper;

import java.util.List;

import com.alimama.api.model.Employee;
import com.alimama.api.model.EmployeeExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmployeeMapper {
    int countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    Long updateByPrimaryKey(Employee record);

    List<Employee> selectPageByExample(EmployeeExample employeeExample, @Param("pageNum")int page, @Param("pageSize")int limit);

    List<Employee> selectPage( @Param("pageNum")int page, @Param("pageSize")int limit);

    Integer batchDeleteEmp(@Param(value = "ids") List<Long> ids);
}