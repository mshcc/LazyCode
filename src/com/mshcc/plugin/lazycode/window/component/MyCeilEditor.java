package com.mshcc.plugin.lazycode.window.component;

import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.table.JBTable;
import com.mshcc.plugin.lazycode.window.component.listener.browse.DefaultTextBrowseFolderListener;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;
import java.awt.*;

import static com.mshcc.plugin.lazycode.complex.GlobalConstant.FILE_CHOOSER_DESCRIPTOR;
import static com.mshcc.plugin.lazycode.complex.GlobalConstant.PROJECT;

/**
 * @author mshcc
 */
public class MyCeilEditor extends AbstractCellEditor implements TableCellEditor, TreeCellEditor {

    TextFieldWithBrowseButton field;

    public MyCeilEditor() {
    }

    @Override
    public Component getTableCellEditorComponent(JTable jTable, Object o, boolean b, int i, int i1) {
        field = new TextFieldWithBrowseButton();
        field.addBrowseFolderListener(new DefaultTextBrowseFolderListener(FILE_CHOOSER_DESCRIPTOR, PROJECT, (JBTable) jTable, i, i1));
        MyTableModel model = (MyTableModel) jTable.getModel();
        field.setText((String) model.getValueAt(i,i1));
        return field;
    }

    @Override
    public Component getTreeCellEditorComponent(JTree jTree, Object o, boolean b, boolean b1, boolean b2, int i) {
        return null;
    }

    @Override
    public Object getCellEditorValue() {
        return field.getText();
    }
}
