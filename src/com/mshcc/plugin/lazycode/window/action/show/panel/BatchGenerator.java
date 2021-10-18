package com.mshcc.plugin.lazycode.window.action.show.panel;

import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.mshcc.plugin.lazycode.entity.GeneratorConfig;
import com.mshcc.plugin.lazycode.entity.Table;
import com.mshcc.plugin.lazycode.util.CheckUtil;
import com.mshcc.plugin.lazycode.window.component.JCheckBoxList;
import com.mshcc.plugin.lazycode.window.component.listener.browse.DefaultTextBrowseFolderListener;
import com.mshcc.plugin.lazycode.window.dialog.DialogUtil;

import javax.swing.*;
import java.util.List;

import static com.mshcc.plugin.lazycode.complex.GlobalConstant.FILE_CHOOSER_DESCRIPTOR;
import static com.mshcc.plugin.lazycode.complex.GlobalConstant.PROJECT;

/**
 * @author mshcc
 * @Date 2021/7/26 19:58
 * @Description TODO
 */
public class BatchGenerator {
    public static final BatchGenerator BATCH_GENERATOR = new BatchGenerator();
    private JList tables;
    private JCheckBox selectAll;
    private TextFieldWithBrowseButton codeDir;
    private JPanel batchPanel;
    private List<Table> list;

    public static JComponent getComponent() {
        return BATCH_GENERATOR.batchPanel;
    }


    public static void setTables(List<Table> list) {
        BATCH_GENERATOR.list = list;
        BATCH_GENERATOR.tables.setListData(generatorArray(list));
        {
            BATCH_GENERATOR.codeDir.addBrowseFolderListener(new DefaultTextBrowseFolderListener(FILE_CHOOSER_DESCRIPTOR, PROJECT));
            BATCH_GENERATOR.tables.setSelectionModel(new DefaultListSelectionModel() {
                @Override
                public void setSelectionInterval(int index0, int index1) {
                    if (super.isSelectedIndex(index0)) {
                        super.removeSelectionInterval(index0, index1);
                    } else {
                        super.addSelectionInterval(index0, index1);
                    }
                }
            });
            BATCH_GENERATOR.tables.addListSelectionListener(e ->
                    BATCH_GENERATOR.selectAll.setSelected(BATCH_GENERATOR.tables.getSelectedIndices().length == list.size()));
            JCheckBoxList cell = new JCheckBoxList();
            BATCH_GENERATOR.tables.setCellRenderer(cell);

            BATCH_GENERATOR.selectAll.addActionListener(e -> {
                if (BATCH_GENERATOR.selectAll.isSelected()) {
                    for (int i = 0; i < list.size(); i++) {
                        if (!BATCH_GENERATOR.tables.isSelectedIndex(i)) {
                            BATCH_GENERATOR.tables.setSelectedIndex(i);
                        }
                    }
                } else {
                    BATCH_GENERATOR.tables.clearSelection();
                }
            });
        }

    }

    private static String[] generatorArray(List<Table> list) {
        String[] tbs = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tbs[i] = list.get(i).toString();
        }
        return tbs;
    }

    public static List<String> getSelectedTables() {
        return BATCH_GENERATOR.tables.getSelectedValuesList();
    }

    /**
     * @return 获取生成对象信息
     */
    public static GeneratorConfig getGeneratorConfig() {
        if (CheckUtil.isNull(BATCH_GENERATOR.codeDir)) {
            DialogUtil.showMsg("代码生成目录不能为空");
            return null;
        }
        return new GeneratorConfig.Builder()
                .codeDir(BATCH_GENERATOR.codeDir.getText())
                .build();
    }

}
