package com.example.web.system.entity.vo;

import com.example.web.system.entity.SysUser;
import lombok.Data;

@Data
public class LoginVo<T> {
    private T user;
    /**
     * accessToken
     */
    private String accessToken;
}
