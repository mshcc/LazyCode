package com.mshcc.plugin.lazycode.window;

import com.intellij.ui.table.JBTable;
import com.mshcc.plugin.lazycode.complex.SettingState;
import com.mshcc.plugin.lazycode.complex.custom.Field;
import com.mshcc.plugin.lazycode.complex.custom.Template;
import com.mshcc.plugin.lazycode.entity.SettingConfig;
import com.mshcc.plugin.lazycode.window.component.MyCeilEditor;
import com.mshcc.plugin.lazycode.window.component.MyTableModel;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @Author: mshcc
 * @Date: 2021/10/18  13:42
 * @Description:
 */
public class Setting {


    public static final MyTableModel TEMPLATE_MODEL = new MyTableModel(new String[]{"", "模板名称", "模板位置"});
    public static final MyTableModel FIELDS_MODEL = new MyTableModel(new String[]{"", "字段名称", "字段值"});
    public static final Setting SETTING = new Setting();

    private JPanel panel;
    private JBTable template;
    private JBTable fields;
    private JButton addT;
    private JButton removeT;
    private JButton addF;
    private JButton removeF;

    public JComponent getComponent() {
        return SETTING.panel;
    }

    public void apply() {
        SettingConfig config = SettingState.getInstance().getState();
        if (config == null) {
            config = new SettingConfig();
            config.templates = new ArrayList<>();
            config.fields = new ArrayList<>();
        }
        process(config);
    }

    public void refresh() {
        SettingConfig config = SettingState.getInstance().getState();
        TEMPLATE_MODEL.clear();
        FIELDS_MODEL.clear();
        TEMPLATE_MODEL.addRowList(config.templates);
        TEMPLATE_MODEL.inlineTemplate = SettingState.templates.size();
        FIELDS_MODEL.addRowList(config.fields);
        FIELDS_MODEL.inlineTemplate = SettingState.fields.size();

        template.getTableHeader().setReorderingAllowed(false);
        template.getTableHeader().setResizingAllowed(false);
        fields.getTableHeader().setReorderingAllowed(false);
        fields.getTableHeader().setResizingAllowed(false);
    }

    /**
     * @param config 将配置写入页面
     */
    public void process(SettingConfig config) {
    }

    /**
     * 重置设置
     */
    public void reset() {

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        SettingConfig state = SettingState.getInstance().getState();
        template = new JBTable(TEMPLATE_MODEL);
        fields = new JBTable(FIELDS_MODEL);
        template.getColumnModel().getColumn(0)
                .setCellEditor(template.getDefaultEditor(Boolean.class));
        fields.getColumnModel().getColumn(0)
                .setCellEditor(fields.getDefaultEditor(Boolean.class));
        template.getColumnModel().getColumn(2).setCellEditor(new MyCeilEditor());

        assert state != null;
        TEMPLATE_MODEL.addRowList(state.templates);
        FIELDS_MODEL.addRowList(state.fields);
        template.getTableHeader().setReorderingAllowed(false);
        fields.getTableHeader().setReorderingAllowed(false);
        template.getColumnModel().getColumn(0).setMaxWidth(30);
        fields.getColumnModel().getColumn(0).setMaxWidth(30);
    }

    public SettingConfig getConfig() {
        SettingConfig config = new SettingConfig();
        config.templates = TEMPLATE_MODEL.getList(new Template());
        config.fields = FIELDS_MODEL.getList(new Field());
        return config;
    }

    public void init() {
        addT.addActionListener(actionEvent -> {
            TEMPLATE_MODEL.addRow(new Object[]{true, "", ""});
            int row = TEMPLATE_MODEL.getRowCount() - 1;
            TEMPLATE_MODEL.fireTableRowsInserted(row, row);
        });
        addF.addActionListener(actionEvent -> {
            FIELDS_MODEL.addRow(new Object[]{true, "", ""});
            int row = FIELDS_MODEL.getRowCount() - 1;
            FIELDS_MODEL.fireTableRowsInserted(row, row);
        });
        removeF.addActionListener(actionEvent -> {
            int row = fields.getSelectedRow();
            FIELDS_MODEL.removeRow(row);
            FIELDS_MODEL.fireTableRowsDeleted(row, row);
        });
        removeT.addActionListener(actionEvent -> {
            int row = template.getSelectedRow();
            TEMPLATE_MODEL.removeRow(row);
            TEMPLATE_MODEL.fireTableRowsDeleted(row, row);
        });
    }
}
