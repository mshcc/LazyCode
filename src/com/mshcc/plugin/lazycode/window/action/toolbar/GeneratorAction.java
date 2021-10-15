package com.mshcc.plugin.lazycode.window.action.toolbar;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.mshcc.plugin.lazycode.window.action.show.GeneratorShow;
import org.jetbrains.annotations.NotNull;
/**
 * @author mshcc
 * @date 2021/9/29 17:14
 * @description 代码生成按钮触发动作
 */
public class GeneratorAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        GeneratorShow.initPanel();
    }
}
