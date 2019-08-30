package com.common.core.basebean;

import lombok.Data;

import java.util.List;

@Data
public class MenusTree {
    /**
     * id
     */
    public String id;
    /**
     * 父id
     */
    public String pid;
    /**
     * name
     */
    public String name;
    /**
     * 图标
     */
    public String icon;
    /**
     * 路由url
     */
    public String url;
    /**
     * 排序
     */
    public Integer seq;
    /**
     * 是否被选中
     */
    public boolean flag;
    /**
     * 子菜单
     */
    public List<MenusTree> children;


}
