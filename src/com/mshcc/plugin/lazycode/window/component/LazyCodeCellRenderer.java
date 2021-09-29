package com.mshcc.plugin.lazycode.window.component;

import com.mshcc.plugin.lazycode.complex.GlobalConstant;
import com.mshcc.plugin.lazycode.entity.TreeNode;
import sun.swing.DefaultLookup;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

/*
 * @Author msh
 * @Date 2021/9/29 下午1:31
 * @Description 绘制树形视图
 */
public class LazyCodeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        selected = sel;
        setText(value.toString());
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) value;
        TreeNode node = (TreeNode) root.getUserObject();
        Color fg;
        JTree.DropLocation dropLocation = tree.getDropLocation();
        if (dropLocation != null
                && dropLocation.getChildIndex() == -1
                && tree.getRowForPath(dropLocation.getPath()) == row) {

            Color col = DefaultLookup.getColor(this, ui, "Tree.dropCellForeground");
            if (col != null) {
                fg = col;
            } else {
                fg = getTextSelectionColor();
            }
        } else if (sel) {
            fg = getTextSelectionColor();
        } else {
            fg = getTextNonSelectionColor();
        }

        setForeground(fg);
        if (node != null) {
            if (node.type.equals(GlobalConstant.TREE_DATABASE)) {
                URL url = this.getClass().getResource("/icons/database.png");
                assert url != null : "database Icon not exist";
                Icon icon = new ImageIcon(url);
                super.setIcon(icon);
            } else if (node.type.equals(GlobalConstant.TREE_TABLE)) {
                URL url = this.getClass().getResource("/icons/table.png");
                assert url != null : "table Icon not exist";
                Icon icon = new ImageIcon(url);
                super.setIcon(icon);
            }
        }
        return this;
    }

}

