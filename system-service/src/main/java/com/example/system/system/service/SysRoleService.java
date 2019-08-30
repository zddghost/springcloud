package com.example.system.system.service;


import com.common.core.basebean.MenusTree;
import com.example.system.system.entity.from.MenusSave;

import java.util.List;

public interface SysRoleService {
    List<MenusTree> permission(String roleId);

    void permission(MenusSave menusSave);
}
