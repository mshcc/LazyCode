package com.mshcc.plugin.lazycode.window.action.toolbar;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.mshcc.plugin.lazycode.window.action.show.AddConnectionShow;
import org.jetbrains.annotations.NotNull;


/**
 * @author mshcc
 * @date 2021/9/29 17:12
 * @Description 新增链接按钮触发动作
 */
public class AddConnectionAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        AddConnectionShow.initPanel();
    }
}
