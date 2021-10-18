package com.mshcc.plugin.lazycode.window.action.toolbar;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.mshcc.plugin.lazycode.complex.GlobalConstant;
import com.mshcc.plugin.lazycode.complex.Update;
import org.jetbrains.annotations.NotNull;

/**
 * @author mshcc
 * @date 2021/9/29 17:14
 * @description 刷新插件页面按钮触发动作
 */
public class RefreshAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        GlobalConstant.CURRENT_SELECTED_DATABASE = null;
        GlobalConstant.CURRENT_SELECTED_NODE = null;
        GlobalConstant.CURRENT_SELECTED_PATH = null;
        GlobalConstant.CURRENT_SELECTED_TABLE = null;
        Update.updateTreeView();
    }
}
