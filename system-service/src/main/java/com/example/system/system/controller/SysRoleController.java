package com.example.system.system.controller;

import com.common.core.basebean.MenusTree;
import com.common.core.basebean.ResponseBean;
import com.common.core.basecontroller.BaseController;
import com.example.system.system.entity.from.MenusSave;
import com.example.system.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class SysRoleController extends BaseController {
    @Autowired
    SysRoleService sysRoleService;

    /**
     * 获取到角色所有的菜单
     *
     * @param roleId
     * @return
     */
    @GetMapping(value = "permission/{roleId}")
    public ResponseBean permission(@PathVariable String roleId) {
        List<MenusTree> list = sysRoleService.permission(roleId);
        return success(list);
    }

    /**
     * 保存角色菜单
     *
     * @param menusSave
     * @return
     */
    @PostMapping(value = "permission")
    public ResponseBean permission(@RequestBody MenusSave menusSave) {
        sysRoleService.permission(menusSave);
        return success();
    }
}
