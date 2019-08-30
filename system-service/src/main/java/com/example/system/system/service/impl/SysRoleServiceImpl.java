package com.example.system.system.service.impl;


import com.common.core.basebean.MenusTree;
import com.common.core.utils.TreeNodeUtil;
import com.example.system.system.dao.SysPermissionMapper;
import com.example.system.system.dao.SysRoleMapper;
import com.example.system.system.entity.from.MenusSave;
import com.example.system.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @Override
    public List<MenusTree> permission(String roleId) {
        List<MenusTree> allMenus = sysPermissionMapper.menusAll();
        List<MenusTree> selectMenus = sysRoleMapper.menus(roleId);
        allMenus=TreeNodeUtil.getMenusTree(allMenus,selectMenus);
        return allMenus;
    }
    @Transactional
    @Override
    public void permission(MenusSave menusSave) {
        String roleId = menusSave.getRoleId();
        if(!StringUtils.isEmpty(roleId)){
            sysRoleMapper.deleteByRole(roleId);
            int size = menusSave.getIds().size();
            if(0<size){
                sysRoleMapper.save(menusSave);
            }
        }

    }



}
