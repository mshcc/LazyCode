package com.mshcc.plugin.lazycode.window.action.show;

import com.intellij.openapi.ui.DialogWrapper;
import com.mshcc.plugin.lazycode.complex.Update;
import com.mshcc.plugin.lazycode.entity.DbConfig;
import com.mshcc.plugin.lazycode.util.DbUtil;
import com.mshcc.plugin.lazycode.util.IOUtil;
import com.mshcc.plugin.lazycode.window.action.show.panel.EditConnection;
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
public class EditConnectionShow extends DialogWrapper {

    private DbConfig config;

    protected EditConnectionShow(DbConfig config) {
        super(true);
        setResizable(false);
        this.config = config;
        init();
        setTitle("编辑连接");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        EditConnection.setDbConfig(config);
        return EditConnection.getPanel();
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        TestAction testAction = new TestAction();
        SaveAction saveAction = new SaveAction();
        testAction.putValue(DialogWrapper.DEFAULT_ACTION, true);
        return new Action[]{testAction, saveAction};
    }

    public static void initPanel(DbConfig config) {
        new EditConnectionShow(config).showAndGet();
    }

    class SaveAction extends DialogWrapperAction {

        protected SaveAction() {
            super("保存修改");
        }

        @Override
        protected void doAction(ActionEvent actionEvent) {
            DbConfig dbConfig = EditConnection.getDbConfig(false);
            if (dbConfig != null) {
                for (DbConfig c : DATABASES_CONFIG_LIST) {
                    if (c != config
                            && c.getHost().equals(dbConfig.getHost())
                            && c.getPort().equals(dbConfig.getPort())
                            && c.getUsername().equals(dbConfig.getUsername())
                            && c.getSchema().equals(dbConfig.getSchema())) {
                        DialogUtil.showMsg("连接已存在，连接名为：".concat(config.getConnName()));
                        close(CANCEL_EXIT_CODE);
                        return;
                    }
                }
                DATABASES_CONFIG_LIST.remove(config);
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
            DbConfig dbConfig = EditConnection.getDbConfig(true);
            if (dbConfig != null) {
                DialogUtil.showMsg(DbUtil.checkConnection(dbConfig) ? "连接成功" : "连接失败");
            }
        }
    }
}
