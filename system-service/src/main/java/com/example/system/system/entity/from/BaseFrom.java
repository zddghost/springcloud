package com.example.system.system.entity.from;

import lombok.Data;

@Data
public class BaseFrom {
    private Integer pageNum;
    private Integer pageSize;
    private String query;
}
