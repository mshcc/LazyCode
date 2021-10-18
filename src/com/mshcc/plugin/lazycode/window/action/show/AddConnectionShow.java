package com.mshcc.plugin.lazycode.window.action.show;

import com.intellij.openapi.ui.DialogWrapper;
import com.mshcc.plugin.lazycode.complex.Update;
import com.mshcc.plugin.lazycode.entity.DbConfig;
import com.mshcc.plugin.lazycode.util.DbUtil;
import com.mshcc.plugin.lazycode.util.IOUtil;
import com.mshcc.plugin.lazycode.window.action.show.panel.AddConnection;
import com.mshcc.plugin.lazycode.window.dialog.DialogUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static com.mshcc.plugin.lazycode.complex.GlobalConstant.DATABASES_CONFIG_LIST;

/**
 * @author mshcc
 * @Date 2021/9/29 17:17
 * @Description 新增链接窗口展示
 */
public class AddConnectionShow extends DialogWrapper {
    protected AddConnectionShow() {
        super(true);
        setResizable(false);
        init();
        setTitle("新增连接");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return AddConnection.getPanel();
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        TestAction testAction = new TestAction();
        SaveAction saveAction = new SaveAction();
        testAction.putValue(DialogWrapper.DEFAULT_ACTION, true);
        return new Action[]{testAction, saveAction};
    }

    public static void initPanel() {
        new AddConnectionShow().showAndGet();
    }

    class SaveAction extends DialogWrapperAction {

        protected SaveAction() {
            super("保存连接");
        }

        @Override
        protected void doAction(ActionEvent actionEvent) {
            DbConfig dbConfig = AddConnection.getDbConfig(false);
            if (dbConfig != null) {
                for (DbConfig config : DATABASES_CONFIG_LIST) {
                    if (config.getConnName().equals(dbConfig.getConnName())) {
                        DialogUtil.showMsg("连接名称重复，请修改");
                        return;
                    }
                    if (config.getHost().equals(dbConfig.getHost()) && config.getPort().equals(dbConfig.getPort())
                            && config.getUsername().equals(dbConfig.getUsername()) && config.getSchema().equals(dbConfig.getSchema())) {
                        DialogUtil.showMsg("连接已存在，连接名为：".concat(config.getConnName()));
                        close(CANCEL_EXIT_CODE);
                        return;
                    }
                }
                AddConnection.clear();
                DATABASES_CONFIG_LIST.add(dbConfig);
                IOUtil.writeDatabaseConfigList();
                Update.updateTreeView();
                close(CANCEL_EXIT_CODE);
            }
        }
    }

    class TestAction extends DialogWrapperAction {

        protected TestAction() {
            super("测试连接");
        }

        @Override
        protected void doAction(ActionEvent actionEvent) {
            DbConfig dbConfig = AddConnection.getDbConfig(true);
            if (dbConfig != null) {
                DialogUtil.showMsg(DbUtil.checkConnection(dbConfig) ? "连接成功" : "连接失败");
            }
        }
    }
}
