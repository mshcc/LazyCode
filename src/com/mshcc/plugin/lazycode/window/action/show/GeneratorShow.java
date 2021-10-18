package com.mshcc.plugin.lazycode.window.action.show;

import com.intellij.openapi.ui.DialogWrapper;
import com.mshcc.plugin.lazycode.window.action.show.panel.Generator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author mshcc
 * @Date 2021/9/29 17:18
 * @Description 代码生成窗口展示
 */
public class GeneratorShow extends DialogWrapper {
    protected GeneratorShow() {
        super(true);
        // 固定窗口大小
        setResizable(false);
        init();
        setTitle("单表生成");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        Generator.init();
        return Generator.getGeneratorPanel();
    }

    public static void initPanel() {
        new GeneratorShow().showAndGet();
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
            System.out.println(Generator.getGeneratorConfig());
//            DialogUtil.showMsg("点击了");
//            执行代码生成...
        }
    }
}
