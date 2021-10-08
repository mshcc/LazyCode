package com.mshcc.plugin.lazycode.window.action.show;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author mshcc
 * @Date 2021/9/29 17:18
 * @Description 删除链接窗口展示
 */
public class DelConnectionShow extends DialogWrapper {
    protected DelConnectionShow() {
        super(true);
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return null;
    }

    public static void initPanel() {
        new DelConnectionShow().showAndGet();
    }
}
