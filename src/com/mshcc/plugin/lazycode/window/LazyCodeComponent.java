package com.mshcc.plugin.lazycode.window;

import com.intellij.openapi.actionSystem.DataProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;
import com.mshcc.plugin.lazycode.complex.Init;

/**
 * @Author msh
 * @Date 2021/9/29 下午2:12
 * @Description 创建插件组件窗口
 */
public class LazyCodeComponent  extends SimpleToolWindowPanel implements DataProvider {

    public LazyCodeComponent() {
        super(true,true);
        Init.initTree();
        setToolbar(Init.initToolBar());
        setContent(Init.initContent());
        Init.initTreeView();
    }

}
