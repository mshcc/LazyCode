package com.mshcc.plugin.lazycode;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.mshcc.plugin.lazycode.complex.Init;
import com.mshcc.plugin.lazycode.window.LazyCodeComponent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @Author msh
 * @Date 2021/9/28 下午4:56
 * @Description 插件的入口
 */
public class LazyCodeWindow implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        Init.initProject(project);
        // 关键在这里实例化你像在工具栏中展示的组件
        JComponent component = new LazyCodeComponent();
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(component, "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
