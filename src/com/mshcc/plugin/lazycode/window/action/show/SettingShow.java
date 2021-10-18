package com.mshcc.plugin.lazycode.window.action.show;

import com.intellij.openapi.ui.DialogWrapper;
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
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return null;
    }

    public static void initPanel() {
        new SettingShow().showAndGet();
    }
}
