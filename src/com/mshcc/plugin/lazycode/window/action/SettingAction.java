package com.mshcc.plugin.lazycode.window.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.mshcc.plugin.lazycode.window.action.show.SettingShow;
import org.jetbrains.annotations.NotNull;
/**
 * @author mshcc
 * @date 2021/9/29 17:15
 * @description 设置按钮触发动作
 * TODO： 后续将新增一些插槽，即用户自定义模板、选择外部jdbc库等
 */
public class SettingAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        SettingShow.initPanel();
    }
}
