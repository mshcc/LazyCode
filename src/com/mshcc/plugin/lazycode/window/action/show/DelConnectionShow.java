package com.mshcc.plugin.lazycode.window.action.show;

import com.intellij.openapi.ui.DialogWrapper;
import com.mshcc.plugin.lazycode.window.action.common.DelConnectionAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static com.mshcc.plugin.lazycode.complex.GlobalConstant.*;
/**
 * @author mshcc
 * @Date 2021/9/29 17:18
 * @Description 删除链接窗口展示
 */
public class DelConnectionShow extends DialogWrapper {
    protected DelConnectionShow() {
        super(true);
        setResizable(false);
        init();
        setTitle("删除连接");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("即将删除名为" + CURRENT_SELECTED_DATABASE.getConnName() + "数据库连接");
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    public static void initPanel() {
        new DelConnectionShow().showAndGet();
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        DialogWrapperExitAction exitAction = new ExitAction("取消", CANCEL_EXIT_CODE);
        exitAction.putValue(DialogWrapper.DEFAULT_ACTION, true);
        return new Action[]{new ConfirmAction(), exitAction};
    }

    class ExitAction extends DialogWrapperExitAction{
        public ExitAction(String name, int exitCode) {
            super(name, exitCode);
        }
        @Override
        protected void doAction(ActionEvent e) {
            super.doAction(e);
        }
    }

    class ConfirmAction extends DialogWrapperAction {
        protected ConfirmAction() {
            super("确定");
        }
        @Override
        protected void doAction(ActionEvent actionEvent) {
            DelConnectionAction.DO_DELETE = true;
            close(CANCEL_EXIT_CODE);
        }
    }
}
