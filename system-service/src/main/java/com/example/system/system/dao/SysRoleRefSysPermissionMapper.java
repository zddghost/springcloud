package com.example.system.system.dao;

import com.example.system.system.entity.SysRoleRefSysPermission;
import com.example.system.system.entity.SysRoleRefSysPermissionExample;
import com.example.system.system.entity.SysRoleRefSysPermission;
import com.example.system.system.entity.SysRoleRefSysPermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleRefSysPermissionMapper {
    int countByExample(SysRoleRefSysPermissionExample example);

    int deleteByExample(SysRoleRefSysPermissionExample example);

    int insert(SysRoleRefSysPermission record);

    int insertSelective(SysRoleRefSysPermission record);

    List<SysRoleRefSysPermission> selectByExample(SysRoleRefSysPermissionExample example);

    int updateByExampleSelective(@Param("record") SysRoleRefSysPermission record, @Param("example") SysRoleRefSysPermissionExample example);

    int updateByExample(@Param("record") SysRoleRefSysPermission record, @Param("example") SysRoleRefSysPermissionExample example);
}