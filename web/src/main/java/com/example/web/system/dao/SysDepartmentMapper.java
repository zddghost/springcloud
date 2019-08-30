package com.example.web.system.dao;

import com.example.web.system.entity.SysDepartment;
import com.example.web.system.entity.SysDepartmentExample;
import com.example.web.system.entity.SysDepartment;
import com.example.web.system.entity.SysDepartmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDepartmentMapper {
    int countByExample(SysDepartmentExample example);

    int deleteByExample(SysDepartmentExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysDepartment record);

    int insertSelective(SysDepartment record);

    List<SysDepartment> selectByExampleWithBLOBs(SysDepartmentExample example);

    List<SysDepartment> selectByExample(SysDepartmentExample example);

    SysDepartment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysDepartment record, @Param("example") SysDepartmentExample example);

    int updateByExampleWithBLOBs(@Param("record") SysDepartment record, @Param("example") SysDepartmentExample example);

    int updateByExample(@Param("record") SysDepartment record, @Param("example") SysDepartmentExample example);

    int updateByPrimaryKeySelective(SysDepartment record);

    int updateByPrimaryKeyWithBLOBs(SysDepartment record);

    int updateByPrimaryKey(SysDepartment record);
}