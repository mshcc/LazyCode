package com.mshcc.plugin.lazycode.window.action.common;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.mshcc.plugin.lazycode.window.action.show.BatchGeneratorShow;
import org.jetbrains.annotations.NotNull;

import static com.mshcc.plugin.lazycode.complex.GlobalConstant.CURRENT_SELECTED_DATABASE;

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

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabled(CURRENT_SELECTED_DATABASE != null);
    }
}
