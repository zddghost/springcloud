package com.example.web.system.service;


import com.example.web.system.entity.from.MenusSave;
import com.example.web.system.entity.vo.MenusTree;

import java.util.List;

public interface SysRoleService {
    List<MenusTree> permission(String roleId);

    void permission(MenusSave menusSave);
}
