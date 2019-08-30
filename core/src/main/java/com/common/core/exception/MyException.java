package com.common.core.exception;

import com.common.core.enumset.EnumSet;

/**
 * @Auther: cookie
 * @Date: 2018/7/26 15:22
 * @Description:
 */

public class MyException extends RuntimeException {

    private Integer code;  //异常状态码

    private String message;  //异常信息

    private String method;   //发生的方法，位置等

    private String descinfo;   //描述

    public MyException() {

    }

    public MyException(EnumSet enumSet) {
        this.code = enumSet.getCode();
        this.message = enumSet.getMessage();
    }

    public MyException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDescinfo() {
        return descinfo;
    }

    public void setDescinfo(String descinfo) {
        this.descinfo = descinfo;
    }
}
