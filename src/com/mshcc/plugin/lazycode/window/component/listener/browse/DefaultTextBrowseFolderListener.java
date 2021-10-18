package com.mshcc.plugin.lazycode.window.component.listener.browse;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    }
}
