package com.mshcc.plugin.lazycode.window.action.menu.database;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.mshcc.plugin.lazycode.window.action.show.EditConnectionShow;
import org.jetbrains.annotations.NotNull;

import static com.mshcc.plugin.lazycode.complex.GlobalConstant.CURRENT_SELECTED_DATABASE;

/**
 * @Author: mshcc
 * @Date: 2021/10/15  10:36
 * @Description:
 */
public class ConnectionEditAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        EditConnectionShow.initPanel(CURRENT_SELECTED_DATABASE);
    }
}
