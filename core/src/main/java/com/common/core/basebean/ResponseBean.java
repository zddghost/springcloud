package com.common.core.basebean;

import lombok.Data;

@Data
public class ResponseBean<T> {
    private Integer code;
    private String msg;
    private T data;
}
