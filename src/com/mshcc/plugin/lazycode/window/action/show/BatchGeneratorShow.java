package com.mshcc.plugin.lazycode.window.action.show;

import com.intellij.openapi.ui.DialogWrapper;
import com.mshcc.plugin.lazycode.complex.GlobalConstant;
import com.mshcc.plugin.lazycode.util.DbUtil;
import com.mshcc.plugin.lazycode.window.action.show.panel.BatchGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author mshcc
 * @Date 2021/9/29 17:20
 * @Description 批量代码生成窗口展示
 */
public class BatchGeneratorShow extends DialogWrapper {
    protected BatchGeneratorShow() {
        super(true);
        // 固定窗口大小
        setResizable(false);
        setSize(200, 400);
        init();
        setTitle("多表生成");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        BatchGenerator.setTables(DbUtil.getTables(GlobalConstant.CURRENT_SELECTED_DATABASE));
        return BatchGenerator.getComponent();
    }

    public static void initPanel() {
        new BatchGeneratorShow().showAndGet();
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        GeneratorAction generatorAction = new GeneratorAction();
        generatorAction.putValue(DialogWrapper.DEFAULT_ACTION, true);
        return new Action[]{generatorAction};
    }

    class GeneratorAction extends DialogWrapperAction {
        protected GeneratorAction() {
            super("代码生成");
        }

        @Override
        protected void doAction(ActionEvent actionEvent) {
            System.out.println(BatchGenerator.getGeneratorConfig());
            for (String table : BatchGenerator.getSelectedTables()) {
                System.out.println("table = " + table);
            }
//            DialogUtil.showMsg("点击了");
//            执行代码生成...
        }
    }
}
