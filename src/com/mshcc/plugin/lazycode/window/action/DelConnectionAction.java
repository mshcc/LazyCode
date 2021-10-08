package com.mshcc.plugin.lazycode.window.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.mshcc.plugin.lazycode.window.action.show.DelConnectionShow;
import org.jetbrains.annotations.NotNull;

/**
 * @author mshcc
 * @date 2021/9/29 17:13
 * @description 删除链接按钮动作
 */
public class DelConnectionAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        DelConnectionShow.initPanel();
    }
}
