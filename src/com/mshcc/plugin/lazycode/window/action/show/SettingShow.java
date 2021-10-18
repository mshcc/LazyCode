package com.mshcc.plugin.lazycode.window.action.show;

import com.intellij.openapi.ui.DialogWrapper;
import com.mshcc.plugin.lazycode.window.action.show.panel.Setting;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author mshcc
 * @Date 2021/9/29 17:20
 * @Description 设置窗口展示
 */
public class SettingShow extends DialogWrapper {
    protected SettingShow() {
        super(true);
        // 固定窗口大小
        setResizable(false);
        init();
        setTitle("设置");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return new Setting().getComponent();
    }

    public static void initPanel() {
        new SettingShow().showAndGet();
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        return new Action[]{};
    }

}
