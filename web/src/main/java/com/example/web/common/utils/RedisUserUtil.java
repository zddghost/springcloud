package com.example.web.common.utils;

import com.example.web.system.dao.SysUserMapper;
import com.example.web.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RedisUserUtil {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 获取用户
     */
    public SysUser getUser() {
        String accessToken = ContextHolderUtils.getRequest().getHeader("accessToken");
        SysUser user = new SysUser();
        if (!StringUtils.isEmpty(accessToken)) {
            String[] split = accessToken.split("\\.");
            String key = split[1].toLowerCase();
            String jsStr = redisUtil.get(key);
            if (jsStr != null) {
                user = sysUserMapper.selectByPrimaryKey(key);
//                user = JSON.parseObject(JSON.parseObject(jsStr).getString("t"), SysUser.class);
            }
        } else {
            user.setId("1");
        }
        return user;
    }
}
