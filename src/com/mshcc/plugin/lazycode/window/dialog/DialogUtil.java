package com.mshcc.plugin.lazycode.window.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * @Author msh
 * @Date 2021/9/29 下午2:36
 * @Description 消息提示框
 */
public class DialogUtil extends DialogWrapper {
    private final String message;

    private DialogUtil(String message) {
        super(true);
        this.message = message;
        init();
        setTitle("消息提示");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }


    @NotNull
    @Override
    protected Action[] createActions() {
        DialogWrapperExitAction exitAction = new DialogWrapperExitAction("确定", CANCEL_EXIT_CODE);
        exitAction.putValue(DialogWrapper.DEFAULT_ACTION, true);
        return new Action[]{exitAction};
    }

    public static void showMsg(String message) {
        DialogUtil dialog = new DialogUtil(message);
        dialog.showAndGet();
    }
}