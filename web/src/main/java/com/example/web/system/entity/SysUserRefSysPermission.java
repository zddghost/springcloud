package com.example.web.system.entity;

public class SysUserRefSysPermission {
    /** 角色ID*/
    private String sysyUserId;

    /** 权限ID*/
    private String sysPermissionId;

    public String getSysyUserId() {
        return sysyUserId;
    }

    public void setSysyUserId(String sysyUserId) {
        this.sysyUserId = sysyUserId == null ? null : sysyUserId.trim();
    }

    public String getSysPermissionId() {
        return sysPermissionId;
    }

    public void setSysPermissionId(String sysPermissionId) {
        this.sysPermissionId = sysPermissionId == null ? null : sysPermissionId.trim();
    }
}