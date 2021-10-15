package com.mshcc.plugin.lazycode.complex;

import com.mshcc.plugin.lazycode.entity.DbConfig;
import com.mshcc.plugin.lazycode.entity.Table;
import com.mshcc.plugin.lazycode.entity.TreeNode;
import com.mshcc.plugin.lazycode.util.DbUtil;
import com.mshcc.plugin.lazycode.util.IOUtil;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import java.util.List;

import static com.mshcc.plugin.lazycode.complex.GlobalConstant.*;

/**
 * @Author msh
 * @Date 2021/9/29 下午2:13
 * @Description 插件内容更新方法
 */
public class Update {
    public static void updateTreeView() {
        DefaultTreeModel treeMode = (DefaultTreeModel) TREE.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeMode.getRoot();
        root.removeAllChildren();
        DATABASES_CONFIG_LIST = IOUtil.getDatabaseConfigList();
        if (DATABASES_CONFIG_LIST == null) {
            return;
        }
        for (DbConfig config : DATABASES_CONFIG_LIST) {
            root.add(new DefaultMutableTreeNode(new TreeNode(config.getConnName(), GlobalConstant.TREE_DATABASE)));
        }
        TREE.updateUI();
        treeMode.reload();
        // 如何还原原先的状态，展开 | 展开但没完全展开
    }

    public static void expandTreeNode(DefaultMutableTreeNode node, DbConfig dbConfig) {
        List<Table> tables = DbUtil.getTables(dbConfig);
        if (tables != null) {
            for (Table table : tables) {
                node.add(new DefaultMutableTreeNode(new TreeNode(table.toString(), TREE_TABLE)));
            }
            TREE.expandPath(CURRENT_SELECTED_PATH);
        }
    }
}
