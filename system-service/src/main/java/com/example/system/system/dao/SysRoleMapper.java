package com.example.system.system.dao;

import com.common.core.basebean.MenusTree;
import com.example.system.system.entity.SysRole;
import com.example.system.system.entity.SysRoleExample;
import com.example.system.system.entity.from.MenusSave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SysRoleMapper {
    int countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);


    List<MenusTree> menusAll();

    List<MenusTree> menus(String roleId);

    void deleteByRole(String roleId);

    void save(MenusSave menusSave);


}