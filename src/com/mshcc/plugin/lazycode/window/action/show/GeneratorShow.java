package com.mshcc.plugin.lazycode.window.action.show;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author mshcc
 * @Date 2021/9/29 17:18
 * @Description 代码生成窗口展示
 */
public class GeneratorShow extends DialogWrapper {
    protected GeneratorShow() {
        super(true);
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return null;
    }

    public static void initPanel() {
        new GeneratorShow().showAndGet();
    }
}
