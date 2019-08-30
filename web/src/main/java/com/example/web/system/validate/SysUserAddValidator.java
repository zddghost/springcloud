package com.example.web.system.validate;

import com.common.core.enumset.EnumSet;
import com.common.core.exception.MyException;
import com.common.core.validation.Validator;
import com.example.web.common.utils.SpringContextUtils;
import com.example.web.system.dao.SysUserMapper;
import com.example.web.system.entity.SysUser;
import com.example.web.system.entity.SysUserExample;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SysUserAddValidator extends Validator {

    @Override
    public Object validate(Object... t) {
        SysUser sysUser = (SysUser) t[0];
        SysUserMapper sysUserMapper = SpringContextUtils.getBean("sysUserMapper",SysUserMapper.class);
        SysUserExample example=new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        String name = sysUser.getName();
        criteria.andNameEqualTo(name);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        if(sysUsers.size()>0){
            throw new MyException(EnumSet.FAILURE.getCode(),"用户名存在!");
        }
        return null;
    }
}
