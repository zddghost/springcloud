package com.example.web.config.interceptor;


import com.alibaba.fastjson.JSONObject;

import com.common.core.enumset.EnumSet;
import com.common.core.exception.MyException;
import com.example.web.common.utils.RedisUtil;
import com.example.web.system.dao.SysUserMapper;
import com.example.web.system.entity.RedisUser;
import com.example.web.system.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * Created by by yfw
 */
public class LoginInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    SysUserMapper sysUserMapper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        if (checkurl(requestURI)) {
            return true;
        }
        String method = request.getMethod();
        String accessToken = request.getHeader("accessToken");
        logger.info("******目标访问参数******");
        logger.info("目标访问路径:" + requestURI);
        logger.info("目标访问提交方式:" + method);
        logger.info("目标访问提交accessToken:" + accessToken);
        Map<String, String[]> map = request.getParameterMap();
        logger.info("************************");
        map.forEach((k, v) -> {
            logger.info("请求参数-- " + k + ": " + v[0]);
        });
        //校验token
        check(accessToken);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    private boolean checkurl(String requestURI) {
        String[] str = {"/user/login", "/user/code", "/file/download", "/file/image","file/exportExcel","file/importExcel", "/swagger-ui.html", "/webjars", "/swagger-resources", "/v2/api-docs"};
        List<String> urls = Arrays.asList(str);
        for (String url : urls) {
            /*int i = requestURI.indexOf(url);
            if (i != -1) {
                return true;
            }*/
            boolean contains = requestURI.contains(url);
            if (contains) {
                return true;
            }
        }
        return false;
    }

    private void check(String accessToken) {
        if (StringUtils.isEmpty(accessToken)) {
            throw new MyException(EnumSet.USER_LOGIN);
        } else {
            String key = "";
            String s = "";
            try {
                String[] split = accessToken.split("\\.");
                key = split[1].toLowerCase();
            } catch (Exception e) {
                throw new MyException(EnumSet.FAILURE.getCode(), "accessToken解析异常");
            }
            checkAccessToken(accessToken, key);
        }
    }

    private void checkAccessToken(String accessToken, String key) {
        String s = redisUtil.get(key);
        if (StringUtils.isEmpty(s)) {
            throw new MyException(EnumSet.USER_LOGIN);
        }
        RedisUser<SysUser> redisUser = JSONObject.parseObject(s, RedisUser.class);
        String accessTokenRe = redisUser.getAccessToken();
        if (!accessTokenRe.equals(accessToken)) {
            throw new MyException(EnumSet.USER_LOGIN);
        }
        //对accessToken续时
        redisUtil.setObject(key, redisUser, 1800);
    }
}

