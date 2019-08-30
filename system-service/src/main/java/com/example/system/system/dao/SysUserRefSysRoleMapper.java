package com.example.system.system.dao;

import com.example.system.system.entity.SysUserRefSysRole;
import com.example.system.system.entity.SysUserRefSysRoleExample;
import com.example.system.system.entity.SysUserRefSysRole;
import com.example.system.system.entity.SysUserRefSysRoleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SysUserRefSysRoleMapper {
    int countByExample(SysUserRefSysRoleExample example);

    int deleteByExample(SysUserRefSysRoleExample example);

    int insert(SysUserRefSysRole record);

    int insertSelective(SysUserRefSysRole record);

    List<SysUserRefSysRole> selectByExample(SysUserRefSysRoleExample example);

    int updateByExampleSelective(@Param("record") SysUserRefSysRole record, @Param("example") SysUserRefSysRoleExample example);

    int updateByExample(@Param("record") SysUserRefSysRole record, @Param("example") SysUserRefSysRoleExample example);
}