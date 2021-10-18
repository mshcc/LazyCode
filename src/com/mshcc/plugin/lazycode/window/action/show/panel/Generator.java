package com.mshcc.plugin.lazycode.window.action.show.panel;

import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.mshcc.plugin.lazycode.entity.GeneratorConfig;
import com.mshcc.plugin.lazycode.util.CheckUtil;
import com.mshcc.plugin.lazycode.window.component.listener.browse.DefaultTextBrowseFolderListener;
import com.mshcc.plugin.lazycode.window.dialog.DialogUtil;

import javax.swing.*;

import static com.mshcc.plugin.lazycode.complex.GlobalConstant.*;


/**
 * @author mshcc
 * @Date 2021/7/26 19:36
 * @Description TODO
 */
public class Generator {
    public static final Generator GENERATOR = new Generator();

    private JTextField table;
    private TextFieldWithBrowseButton codeDir;
    private JPanel panel;

    public Generator() {
        codeDir.addBrowseFolderListener(new DefaultTextBrowseFolderListener(FILE_CHOOSER_DESCRIPTOR, PROJECT));
    }

    /**
     * 初始化
     */
    public static void init() {
        GENERATOR.table.setText(CURRENT_SELECTED_TABLE);
        GENERATOR.table.setEnabled(false);
        GENERATOR.table.setEditable(false);
        GENERATOR.codeDir.setText("");
    }

    public static JComponent getGeneratorPanel() {
        return GENERATOR.panel;
    }

    /**
     * @return 获取生成对象信息
     */
    public static GeneratorConfig getGeneratorConfig() {
        if (CheckUtil.isNull(GENERATOR.codeDir)) {
            DialogUtil.showMsg("代码生成目录不能为空");
            return null;
        }
        return new GeneratorConfig.Builder()
                .table(GENERATOR.table.getText())
                .codeDir(GENERATOR.codeDir.getText())
                .build();
    }

}
