package com.mshcc.plugin.lazycode.complex;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intellij.openapi.project.Project;
import com.intellij.ui.treeStructure.SimpleTree;
import com.mshcc.plugin.lazycode.entity.DbConfig;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

/*
 * @Author msh
 * @Date 2021/9/28 下午5:17
 * @Description 插件用到的全局属性以及常量
 */
public class GlobalConstant {

    /**
     * 未知类型节点
     */
    public static final Integer TREE_UN_KNOW = -1;

    /**
     * 数据库节点
     */
    public static final Integer TREE_DATABASE = 1;

    /**
     * 数据表节点
     */
    public static final Integer TREE_TABLE = 2;

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

    /**
     * 数据库列表
     */
    public static List<DbConfig> DATABASES_CONFIG_LIST;


    /**
     * gson json解析器，用于数据的序列化和反序列化
     */
    public static final Gson GSON = new Gson();

    /**
     * Gson List反序列化所需类型
     */
    public static final Type DB_CONFIG_LIST_TYPE = new TypeToken<List<DbConfig>>() {
    }.getType();

    public static final String DB_CONFIG_FILE = ".dbConfig";

    /**
     * 配置文件路径
     */
    public static String CONFIGURATION_FILE_PATH = File.pathSeparator.concat(".idea").concat(File.pathSeparator).concat("lazyCode").concat(File.pathSeparator);
}
