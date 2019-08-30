package com.common.core.enumset;

/**
 * 工作空间
 *
 * @author jiang
 */
public enum EnumSet {
    LOGIN(403, "禁止访问!"),
    PERMISSIONS(405, "没有权限!"),
    USER_DISABLE(406, "禁止登陆!"),
    USER_LOGIN(407, "请登录!"),
    SYS_BREAK_ERROR(501, "系统服务中断，请稍后再试"),
    SUCCESS(0, "操作成功!"),
    FAILURE(1, "操作失败!");
    //编码，描述
    private Integer code;
    private String message;

    EnumSet(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
