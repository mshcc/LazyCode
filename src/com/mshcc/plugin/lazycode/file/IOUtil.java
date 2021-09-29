package com.mshcc.plugin.lazycode.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.mshcc.plugin.lazycode.complex.GlobalConstant.*;

/*
 * @Author msh
 * @Date 2021/9/29 下午2:14
 * @Description 与配置文件进行交互
 */
public class IOUtil {


    /**
     * @param <T> 泛型
     * @return 数据库链接信息列表
     */
    public static <T> List<T> getDatabaseConfigList() {
        return getList(DB_CONFIG_LIST_TYPE, DB_CONFIG_FILE);
    }

    /**
     * 将数据库链接信息持久化
     */
    public static void writeDatabaseConfigList() {
        write(GSON.toJson(DATABASES_CONFIG_LIST), DB_CONFIG_FILE);
    }

    /**
     * 将数据保存到文件中
     *
     * @param content  文件内容
     * @param fileName 文件名称
     */
    public static void write(String content, String fileName) {
        try (FileWriter writer = new FileWriter(getFilePath(fileName))) {
            writer.write("[".concat(content).concat("]"));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param type     返回列表的类型
     * @param fileName 配置文件文件名
     * @param <T>      泛型类型
     * @return 列表
     */
    public static <T> List<T> getList(Type type, String fileName) {
        String path = getFilePath(fileName);
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileWriter writer = new FileWriter(path);
                writer.write("[]");
                writer.flush();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return GSON.fromJson(getJSONString(file), type);
    }

    /**
     * 从文件对象中获取JSON数据
     *
     * @param file 文件对象
     * @return JSON数据
     */
    private static String getJSONString(File file) {
        long fileLength = file.length();
        byte[] fileContent = new byte[(int) fileLength];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(fileContent);
            in.close();
        } catch (IOException ignore) {
        }
        return new String(fileContent, StandardCharsets.UTF_8);
    }

    private static String getFilePath(String fileName) {
        return CONFIGURATION_FILE_PATH.concat(fileName);
    }

}
