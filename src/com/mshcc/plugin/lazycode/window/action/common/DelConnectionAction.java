package com.mshcc.plugin.lazycode.window.action.common;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.mshcc.plugin.lazycode.util.IOUtil;
import com.mshcc.plugin.lazycode.window.action.show.DelConnectionShow;
import org.jetbrains.annotations.NotNull;

import javax.swing.tree.DefaultMutableTreeNode;

import static com.mshcc.plugin.lazycode.complex.GlobalConstant.*;

/**
 * @author mshcc
 * @date 2021/9/29 17:13
 * @description 删除链接按钮动作
 */
public class DelConnectionAction extends AnAction {
    public static boolean DO_DELETE = false;

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        DelConnectionShow.initPanel();
        if (DO_DELETE) {
            DATABASES_CONFIG_LIST.remove(CURRENT_SELECTED_DATABASE);
            CURRENT_SELECTED_DATABASE = null;
            IOUtil.writeDatabaseConfigList();
            DefaultMutableTreeNode parent = (DefaultMutableTreeNode) CURRENT_SELECTED_NODE.getParent();
            if (parent != null) {
                parent.remove(CURRENT_SELECTED_NODE);
                TREE.updateUI();
            }
        }
        DO_DELETE = false;
    }


    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabled(CURRENT_SELECTED_DATABASE != null);
    }
}
