package com.example.web.system.service.impl;

import com.common.core.enumset.EnumSet;
import com.common.core.utils.TokenProccessor;
import com.common.core.exception.MyException;
import com.common.core.validation.Validate;
import com.example.web.common.utils.*;
import com.example.web.system.dao.SysPermissionMapper;
import com.example.web.system.dao.SysUserMapper;
import com.example.web.system.entity.RedisUser;
import com.example.web.system.entity.SysUser;
import com.example.web.system.entity.SysUserExample;
import com.example.web.system.entity.SysUserSysPermissionFrom;
import com.example.web.system.entity.from.BaseFrom;
import com.example.web.system.entity.from.LoginFrom;
import com.example.web.system.entity.vo.LoginVo;
import com.example.web.system.entity.vo.MenusTree;
import com.example.web.system.event.LoginOutEvent;
import com.example.web.system.service.SysUserService;
import com.example.web.system.validate.SysUserAddValidator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    RedisUserUtil redisUserUtil;

    @Validate(SysUserAddValidator.class)
    @Override
    public void save(SysUser sysUser) {
        String createuser = redisUserUtil.getUser().getId();
        String id = UUIDUtils.randomUUID();
        sysUser.setId(id);
        sysUser.setCreateuser(createuser);
        sysUser.setCreatedate(new Date());
        sysUserMapper.insertSelective(sysUser);
    }

    @Override
    public SysUser find(String id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(SysUser sysUser) {
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public void permission(SysUserSysPermissionFrom susp) {
        String userId = susp.getUserId();
        if (!StringUtils.isEmpty(userId)) {
            int size = susp.getIds().size();
            sysUserMapper.deletePermission(userId);
            if (0 < size) {
                sysUserMapper.permission(susp);
            }
        }
    }

    /**
     * 获取全部权限
     */
    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @Override
    public List<MenusTree> permissionByUserId(String userId) {
        List<MenusTree> allMenus = sysPermissionMapper.menusAll();
        List<MenusTree> selectMenus = sysUserMapper.permissionByUserId(userId);
        allMenus = TreeNodeUtil.getMenusTree(allMenus, selectMenus);
        return allMenus;
    }

    @Override
    @CacheEvict(value = "users")
    public void delete(String id) {
        sysUserMapper.deleteByPrimaryKey(id);
    }


    @Override
    public List<MenusTree> menus(String userId) {
        List<MenusTree> selectMenus = sysUserMapper.menus(userId);
        selectMenus = TreeNodeUtil.getMenusTree(selectMenus, selectMenus);
        return selectMenus;
    }

    @Autowired
    RedisUtil redisUtil;

    @Override
    public LoginVo login(HttpServletRequest httpServletRequest, LoginFrom loginFrom) {

        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        String userName = loginFrom.getUserName();
        String passWord = loginFrom.getPassWord();
        criteria.andNameEqualTo(userName);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        if (sysUsers.size() > 0) {
            SysUser sysUser = sysUsers.get(0);
            if (0 == sysUser.getStatus()) {
                throw new MyException(EnumSet.USER_DISABLE.getCode(), EnumSet.USER_DISABLE.getMessage());
            } else {
                String passwordRe = sysUser.getPassword();
                if (!passwordRe.equals(passWord)) {
                    throw new MyException(EnumSet.FAILURE.getCode(), "密码错误!");
                }
                String id = sysUser.getId();
                String key = id.toUpperCase();
                String accessToken = TokenProccessor.getInstance().makeToken();
                LoginVo<SysUser> loginVo = new LoginVo();
                loginVo.setUser(sysUser);
                accessToken = accessToken + "." + key;
                loginVo.setAccessToken(accessToken);
                RedisUser<SysUser> redisUser = new RedisUser();
                redisUser.setAccessToken(accessToken);
                redisUser.setT(sysUser);
                redisUtil.setObject(id, redisUser, 1800);
                return loginVo;
            }
        } else {
            throw new MyException(EnumSet.FAILURE.getCode(), "用户不存在!");
        }

    }

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void loginOut(HttpServletRequest httpServletRequest) {
        String accessToken = httpServletRequest.getHeader("accessToken");
        String[] split = accessToken.split("\\.");
        String key = split[1];
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(key);
        String name = sysUser.getName();
        applicationContext.publishEvent(new LoginOutEvent(this, name));
        redisUtil.delete(key);
    }

    @Override
    public PageInfo<SysUser> select(BaseFrom baseFrom) {
        PageHelper.startPage(baseFrom.getPageNum(), baseFrom.getPageSize());
        SysUserExample sysUserExample = new SysUserExample();
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        PageInfo<SysUser> page = new PageInfo<>(sysUsers);
        return page;
    }


}
