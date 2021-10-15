package com.mshcc.plugin.lazycode.complex;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.project.Project;
import com.intellij.ui.treeStructure.SimpleTree;
import com.mshcc.plugin.lazycode.entity.DbConfig;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author msh
 * @Date 2021/9/28 下午5:17
 * @Description 插件用到的全局属性以及常量
 */
public class GlobalConstant {

    /**
     * 未知类型节点
     */
    public static final int TREE_UN_KNOW = -1;

    /**
     * 数据库节点
     */
    public static final int TREE_DATABASE = 1;

    /**
     * 数据表节点
     */
    public static final int TREE_TABLE = 2;

    /**
     * 当前打开的项目路径
     */
    public static String PROJECT_PATH;

    /**
     * 当前打开的项目
     */
    public static Project PROJECT;

    /**
     * 插件主界面的树
     */
    public static SimpleTree TREE;

    /**
     * 当前选中的表名称
     */
    public static String CURRENT_SELECTED_TABLE;

    /**
     * 当前选中的数据库配置
     */
    public static DbConfig CURRENT_SELECTED_DATABASE;


    public static TreePath CURRENT_SELECTED_PATH;
    public static DefaultMutableTreeNode CURRENT_SELECTED_NODE;
    /**
     * 展开的节点
     */
    public static List<String> EXPAND_PATH_LIST = new ArrayList<>();
    /**
     * 展开但没完全展开的节点
     */
    public static List<String> COLLAPSE_PATH_LIST = new ArrayList<>();

    /**
     * 数据库列表
     */
    public static List<DbConfig> DATABASES_CONFIG_LIST;


    /**
     * 配置文件名
     */
    public static final String DB_CONFIG_FILE = ".dbConfig";

    /**
     * 配置文件路径
     */
    public static String CONFIGURATION_FILE_PATH = File.separator.concat(".idea").concat(File.separator).concat("lazyCode").concat(File.separator);


    /**
     * 用于创建工具栏 || 右键菜单
     */
    public static final ActionManager ACTION_MANAGER = ActionManager.getInstance();
}
