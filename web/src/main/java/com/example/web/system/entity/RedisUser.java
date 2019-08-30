package com.example.web.system.entity;

import lombok.Data;

@Data
public class RedisUser<T> {
    private String accessToken;
    private T t;
}
