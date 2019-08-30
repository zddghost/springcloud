package com.example.web.system.controller;

import com.common.core.basebean.ResponseBean;
import com.example.web.common.basecontroller.BaseController;
import com.example.web.system.entity.from.MenusSave;
import com.example.web.system.entity.vo.MenusTree;
import com.example.web.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "角色")
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
    @ApiOperation(value = "获取到角色所有的菜单", notes = "获取到角色所有的菜单")
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
