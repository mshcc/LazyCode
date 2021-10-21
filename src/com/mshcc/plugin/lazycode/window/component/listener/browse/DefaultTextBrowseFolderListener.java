package com.mshcc.plugin.lazycode.window.component.listener.browse;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.table.JBTable;
import com.mshcc.plugin.lazycode.window.component.MyTableModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.table.TableModel;

/**
 * @Author: mshcc
 * @Date: 2021/10/18  10:46
 * @Description: 重写文件选择器填充文本框内容，去除项目根路径
 */
public class DefaultTextBrowseFolderListener extends TextBrowseFolderListener {

    private String type;

    public DefaultTextBrowseFolderListener(@NotNull FileChooserDescriptor fileChooserDescriptor) {
        super(fileChooserDescriptor);
    }

    public DefaultTextBrowseFolderListener(@NotNull FileChooserDescriptor fileChooserDescriptor, @Nullable Project project, String type) {
        super(fileChooserDescriptor, project);
        this.type = type;
    }

    public DefaultTextBrowseFolderListener(@NotNull FileChooserDescriptor fileChooserDescriptor, @Nullable Project project) {
        super(fileChooserDescriptor, project);
        this.type = type;
    }

    private JBTable table;
    private Integer row;
    private Integer col;

    public DefaultTextBrowseFolderListener(@NotNull FileChooserDescriptor fileChooserDescriptor, @Nullable Project project, JBTable table, int row, int col) {
        super(fileChooserDescriptor, project);
        this.type = type;
        this.table = table;
        this.row = row;
        this.col = col;
    }

    @Override
    protected void onFileChosen(@NotNull VirtualFile chosenFile) {
        if (chosenFile == null) {
            return;
        }
        String file = this.chosenFileToResultingText(chosenFile);
        file = file.replace("\\", "/");

        String dir = file;
        if (type != null) {
            if (!dir.endsWith(type)) {
                dir = dir.concat("/").concat(type);
            }
        }
        this.myAccessor.setText(this.myTextComponent, dir);
        if (table != null) {

            MyTableModel model = (MyTableModel) table.getModel();
            model.setValueAt(dir, row, col);
        }
    }
}
