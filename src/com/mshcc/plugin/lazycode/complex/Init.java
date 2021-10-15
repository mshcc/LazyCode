package com.mshcc.plugin.lazycode.complex;

import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.components.JBScrollPane;
import com.mshcc.plugin.lazycode.entity.TreeNode;
import com.mshcc.plugin.lazycode.window.component.LazyCodeCellRenderer;
import com.mshcc.plugin.lazycode.window.component.MySimpleTree;
import com.mshcc.plugin.lazycode.window.component.listener.tree.MyTreeExpansionListener;
import com.mshcc.plugin.lazycode.window.component.listener.tree.MyTreeMouseListener;
import com.mshcc.plugin.lazycode.window.component.listener.tree.MyTreeSelectionListener;
import com.mshcc.plugin.lazycode.window.dialog.DialogUtil;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import static com.mshcc.plugin.lazycode.complex.GlobalConstant.*;

/**
 * @Author msh
 * @Date 2021/9/29 下午2:12
 * @Description 插件的初始化方法
 */
public class Init {

    /**
     * 树节点鼠标监听事件
     */
    public static final MyTreeMouseListener TREE_MOUSE_LISTENER = new MyTreeMouseListener();

    /**
     * 树节点展开事件
     */
    public static final MyTreeExpansionListener TREE_EXPANSION_LISTENER = new MyTreeExpansionListener();

    /**
     * 树节点选择事件
     */
    public static final MyTreeSelectionListener TREE_SELECTION_LISTENER = new MyTreeSelectionListener();

    /**
     * 树形视图渲染器
     */
    public static final LazyCodeCellRenderer LAZY_CODE_CELL_RENDERER = new LazyCodeCellRenderer();

    /**
     * 仅在插件初始化时调用一次，目的是获取当前打开项目
     *
     * @param project 当前打开项目
     */
    public static void initProject(Project project) {
        PROJECT = project;
        PROJECT_PATH = project.getBasePath();
        if (PROJECT == null || PROJECT_PATH == null) {
            DialogUtil.showMsg("插件初始化失败");
        }
        assert PROJECT_PATH != null : "插件初始化失败";
        CONFIGURATION_FILE_PATH = PROJECT_PATH.concat(CONFIGURATION_FILE_PATH);
    }

    /**
     * 初始化插件树形视图
     */
    public static void initTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new TreeNode("root"));
        TREE = new MySimpleTree(new DefaultTreeModel(root));
        TREE.setCellRenderer(LAZY_CODE_CELL_RENDERER);
        TREE.getEmptyText().clear();
        TREE.setOpaque(true);
        TREE.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        TREE.setRootVisible(false);
        TREE.addTreeExpansionListener(TREE_EXPANSION_LISTENER);
        TREE.addTreeSelectionListener(TREE_SELECTION_LISTENER);
        TREE.addMouseListener(TREE_MOUSE_LISTENER);
    }

    /**
     * 初始化插件的工具栏
     */
    public static JComponent initToolBar() {
        ActionToolbar actionToolbar = ACTION_MANAGER.createActionToolbar("lazyCodeToolBar",
                (DefaultActionGroup) ACTION_MANAGER.getAction("lazyCodeToolBar"), false);
        actionToolbar.setTargetComponent(TREE);
        return actionToolbar.getComponent();
    }

    /**
     * 初始化插件组件
     *
     * @return 组件
     */
    public static JComponent initContent() {
        SimpleToolWindowPanel treePanel = new SimpleToolWindowPanel(Boolean.TRUE, Boolean.TRUE);
        JPanel groupPanel = new JPanel();
        groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.Y_AXIS));
        JBScrollPane contentScrollPanel = new JBScrollPane(TREE,
                JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        groupPanel.add(contentScrollPanel);
        treePanel.setContent(groupPanel);
        return treePanel;
    }

    /**
     * 初始化树形视图
     */
    public static void initTreeView() {
        // 虽然还是用了Update，但是初始化里还是要意思一下
        Update.updateTreeView();
    }
}
