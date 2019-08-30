package com.example.web.system.dao;

import com.example.web.system.entity.SysPermission;
import com.example.web.system.entity.SysPermissionExample;
import com.example.web.system.entity.vo.MenusTree;
import com.example.web.system.entity.SysPermission;
import com.example.web.system.entity.SysPermissionExample;
import com.example.web.system.entity.vo.MenusTree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SysPermissionMapper {
    int countByExample(SysPermissionExample example);

    int deleteByExample(SysPermissionExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    List<SysPermission> selectByExampleWithBLOBs(SysPermissionExample example);

    List<SysPermission> selectByExample(SysPermissionExample example);

    SysPermission selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysPermission record, @Param("example") SysPermissionExample example);

    int updateByExampleWithBLOBs(@Param("record") SysPermission record, @Param("example") SysPermissionExample example);

    int updateByExample(@Param("record") SysPermission record, @Param("example") SysPermissionExample example);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKeyWithBLOBs(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    List<MenusTree> menusAll();
}