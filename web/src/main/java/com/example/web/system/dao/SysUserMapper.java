package com.example.web.system.dao;

import com.example.web.system.entity.SysUser;
import com.example.web.system.entity.SysUserExample;
import com.example.web.system.entity.SysUserSysPermissionFrom;
import com.example.web.system.entity.vo.MenusTree;
import com.example.web.system.entity.SysUser;
import com.example.web.system.entity.SysUserExample;
import com.example.web.system.entity.SysUserSysPermissionFrom;
import com.example.web.system.entity.vo.MenusTree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    /**********************自定义接口***********************/
    void permission(SysUserSysPermissionFrom susp);

    void deletePermission(String userId);

    List<MenusTree> permissionByUserId(String userId);

    List<MenusTree> menus(String userId);
}