package com.example.web.system.entity.from;

import lombok.Data;

import java.util.List;

@Data
public class MenusSave {
    /**
     * 主键
     */
    private String roleId;
    /**
     * 权限id
     */
    private List<String> ids;

}
