package com.mshcc.plugin.lazycode.window.action.menu.database;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import static com.mshcc.plugin.lazycode.complex.GlobalConstant.*;

/**
 * @Author: mshcc
 * @Date: 2021/10/15  10:30
 * @Description:
 */
public class ConnectionCloseAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        TREE.collapsePath(CURRENT_SELECTED_PATH);
        CURRENT_SELECTED_NODE.removeAllChildren();
    }
}
