package com.mshcc.plugin.lazycode.entity;

import com.mshcc.plugin.lazycode.complex.GlobalConstant;

/**
 * @Author msh
 * @Date 2021/9/29 上午11:28
 * @Description 树形视图节点，根据type显示相应的icon图标
 */
public class TreeNode {
    public String name;
    public Integer type;

    public TreeNode() {
    }

    public TreeNode(String name) {
        this.name = name;
        this.type = GlobalConstant.TREE_UN_KNOW;
    }

    public TreeNode(String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
}
