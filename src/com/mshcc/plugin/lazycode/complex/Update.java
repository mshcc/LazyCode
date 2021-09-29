package com.mshcc.plugin.lazycode.complex;

import com.mshcc.plugin.lazycode.entity.DbConfig;
import com.mshcc.plugin.lazycode.entity.TreeNode;
import com.mshcc.plugin.lazycode.file.IOUtil;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import static com.mshcc.plugin.lazycode.complex.GlobalConstant.DATABASES_CONFIG_LIST;
import static com.mshcc.plugin.lazycode.complex.GlobalConstant.TREE;

/*
 * @Author msh
 * @Date 2021/9/29 下午2:13
 * @Description 插件内容更新方法
 */
public class Update {
    public static void updateTreeView() {
        DefaultTreeModel treeMode = (DefaultTreeModel) TREE.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeMode.getRoot();
        root.removeAllChildren();
        if (DATABASES_CONFIG_LIST == null) {
            DATABASES_CONFIG_LIST = IOUtil.getDatabaseConfigList();
            if (DATABASES_CONFIG_LIST == null) {
                return;
            }
        }
        for (DbConfig config : DATABASES_CONFIG_LIST) {
            root.add(new DefaultMutableTreeNode(new TreeNode(config.getConnName(), GlobalConstant.TREE_DATABASE)));
        }
        TREE.updateUI();
        treeMode.reload();
    }
}
