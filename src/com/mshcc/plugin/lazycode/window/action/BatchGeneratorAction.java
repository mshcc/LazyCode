package com.mshcc.plugin.lazycode.window.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.mshcc.plugin.lazycode.window.action.show.BatchGeneratorShow;
import org.jetbrains.annotations.NotNull;

/**
 * @author mshcc
 * @date 2021/9/29 17:13
 * @description 批量代码生成按钮触发动作
 */
public class BatchGeneratorAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        BatchGeneratorShow.initPanel();
    }
}
