package com.example.system.system.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysUserSysPermissionFrom implements Serializable {
    private String userId;

    private List<String> ids;
}
