package com.alimama.server.mapper;

import java.util.List;

import com.alimama.api.model.EmployerRole;
import com.alimama.api.model.EmployerRoleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmployerRoleMapper {
    int countByExample(EmployerRoleExample example);

    int deleteByExample(EmployerRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EmployerRole record);

    int insertSelective(EmployerRole record);

    List<EmployerRole> selectByExample(EmployerRoleExample example);

    EmployerRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EmployerRole record, @Param("example") EmployerRoleExample example);

    int updateByExample(@Param("record") EmployerRole record, @Param("example") EmployerRoleExample example);

    int updateByPrimaryKeySelective(EmployerRole record);

    int updateByPrimaryKey(EmployerRole record);
}