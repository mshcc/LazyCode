package com.mshcc.plugin.lazycode.window.component.listener.tree;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.mshcc.plugin.lazycode.complex.Update;
import com.mshcc.plugin.lazycode.entity.DbConfig;
import com.mshcc.plugin.lazycode.entity.TreeNode;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.mshcc.plugin.lazycode.complex.GlobalConstant.*;

/**
 * @Author: mshcc
 * @Date: 2021/10/15  9:33
 * @Description:
 */
public class MyTreeMouseListener extends MouseAdapter {
    private static final int DOUBLE_CLICK = 2;

    @Override
    public void mouseClicked(MouseEvent e) {
        CURRENT_SELECTED_PATH = TREE.getPathForLocation(e.getX(), e.getY());
        if (CURRENT_SELECTED_PATH != null) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) CURRENT_SELECTED_PATH.getLastPathComponent();
            CURRENT_SELECTED_NODE = node;
            TreeNode clicked = (TreeNode) node.getUserObject();
            switch (clicked.type) {
                case TREE_DATABASE:
                    /* 设置当前选中的数据库 */
                    for (DbConfig dbConfig : DATABASES_CONFIG_LIST) {
                        if (dbConfig.getConnName().equals(clicked.name)) {
                            CURRENT_SELECTED_DATABASE = dbConfig;
                            break;
                        }
                    }
                    switch (e.getButton()) {
                        /*左键*/
                        case MouseEvent.BUTTON1:
                            /* 双击未展开节点 */
                            if (e.getClickCount() == DOUBLE_CLICK ) {
                                if(node.getChildCount() == 0){
                                    // 展开数据库的节点
                                    Update.expandTreeNode(node,CURRENT_SELECTED_DATABASE);
                                }
                            }
                            break;
                        /*右键*/
                        case MouseEvent.BUTTON3:
                            //展开右键菜单
                            ActionGroup actionGroup = (ActionGroup) ACTION_MANAGER.getAction("TreeDatabaseRightClickMenu");
                            if (actionGroup != null) {
                                ACTION_MANAGER.createActionPopupMenu("", actionGroup).getComponent().show(e.getComponent(), e.getX(), e.getY());
                            }
                            break;
                        /*滚轮*/
                        case MouseEvent.BUTTON2:
                            // 滚轮时不作为
                            break;
                        default:
                            break;
                    }
                    break;
                case TREE_TABLE:

                    break;
                default:
                    break;
            }

        }
    }
}
