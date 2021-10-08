package com.mshcc.plugin.lazycode.window.action.show;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author mshcc
 * @Date 2021/9/29 17:20
 * @Description 批量代码生成窗口展示
 */
public class BatchGeneratorShow extends DialogWrapper {
    protected BatchGeneratorShow() {
        super(true);
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return null;
    }

    public static void initPanel() {
        new BatchGeneratorShow().showAndGet();
    }
}
