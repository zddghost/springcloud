package com.common.core.utils;


import com.common.core.basebean.MenusTree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreeNodeUtil {

    public  static List<MenusTree> getMenusTree(List<MenusTree> allMenus, List<MenusTree> selectMenus){
        //获取到选中的权限id
        List<String> collect = selectMenus.stream().map(x -> {
            return x.getId();
        }).collect(Collectors.toList());
        //将选中菜单的设置为true
        allMenus = allMenus.stream().map(x -> {
            boolean contains = collect.contains(x.getId());
            if (contains) {
                x.setFlag(true);
            } else {
                x.setFlag(false);
            }
            return x;
        }).collect(Collectors.toList());
        return getfatherNode(allMenus);
    }
    /**
     * 使用递归方法建树
     *
     * @param
     * @return
     */
    public final static List<MenusTree> getfatherNode(List<MenusTree> treeDataList) {

        List<MenusTree> newTreeDataList = new ArrayList<MenusTree>();
        for (MenusTree jsonTreeData : treeDataList) {
            if ("".equals(jsonTreeData.getPid()) || jsonTreeData.getPid() == null) {
                //获取父节点下的子节点
                jsonTreeData.setChildren(getChildrenNode(jsonTreeData.getId(), treeDataList));

                newTreeDataList.add(jsonTreeData);
            }
        }
        return newTreeDataList;
    }

    /**
     * 判断节点是否是根节点
     * @param pid
     * @param treeDataList
     * @return
     */
    private final static List<MenusTree> getChildrenNode(String pid, List<MenusTree> treeDataList) {
        List<MenusTree> newTreeDataList = new ArrayList<MenusTree>();
        for (MenusTree jsonTreeData : treeDataList) {
            if ("".equals(jsonTreeData.getPid()) || jsonTreeData.getPid() == null) continue;
            //这是一个子节点
            if (jsonTreeData.getPid().equals(pid)) {
                //递归获取子节点下的子节点
                jsonTreeData.setChildren(getChildrenNode(jsonTreeData.getId(), treeDataList));
                newTreeDataList.add(jsonTreeData);
            }
        }
        return newTreeDataList;
    }

}
