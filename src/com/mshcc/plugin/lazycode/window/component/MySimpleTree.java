package com.mshcc.plugin.lazycode.window.component;

import com.intellij.ui.treeStructure.SimpleTree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

/*
 * @Author msh
 * @Date 2021/9/29 上午11:52
 * @Description 继承SimpleTree类，重写paintComponent方法，实现树状视图为空时的文字提示
 */
public class MySimpleTree extends SimpleTree {
    // 创建提示信息
    private final JTextPane myPane = new JTextPane();

    {
        myPane.setOpaque(false);
        String message = "添加数据库连接";
        myPane.replaceSelection(message);
    }

    public MySimpleTree() {
    }

    public MySimpleTree(DefaultTreeModel defaultTreeModel) {
        super(defaultTreeModel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);
        } catch (Exception e) {
            return;
        }
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
        if (!root.isLeaf()) {
            return;
        }
        myPane.setFont(getFont());
        myPane.setBackground(getBackground());
        myPane.setForeground(getForeground());
        Rectangle bounds = getBounds();
        myPane.setBounds(0, 0, bounds.width - 10, bounds.height);
        Graphics g2 = g.create(bounds.x + 10, bounds.y + 20, bounds.width, bounds.height);
        try {
            myPane.paint(g2);
        } finally {
            g2.dispose();
        }
    }
}
