package com.mshcc.plugin.lazycode.util;


import com.mshcc.plugin.lazycode.entity.DbConfig;
import com.mshcc.plugin.lazycode.entity.DbType;
import com.mshcc.plugin.lazycode.entity.Field;
import com.mshcc.plugin.lazycode.entity.Table;
import com.mshcc.plugin.lazycode.util.db.MySql;
import com.mshcc.plugin.lazycode.util.db.Oracle;
import com.mshcc.plugin.lazycode.window.dialog.DialogUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author mshcc
 * @Date 2021/5/12 13:38
 * @Description
 */
public class DbUtil {
    public static boolean checkConnection(DbConfig config) {
        try {
            Connection conn = getConnection(config);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Connection getConnection(DbConfig config) throws SQLException {
        DbType dbType = DbType.valueOf(config.getDbType());
        try {
            Class.forName(dbType.getDriverClass());
        } catch (ClassNotFoundException e) {
            DialogUtil.showMsg("数据库驱动不存在");
            throw new RuntimeException("数据库驱动不存在");
        }
        String url = getConnectionUrl(config);
        switch (config.getDbType()) {
            case "MySQL":
            case "MySQL_8":
                return DriverManager.getConnection(url, config.getUsername(), config.getPassword());
            case "Oracle":
                Properties properties = new Properties();
                properties.put("user", config.getUsername());
                properties.put("password", config.getPassword());
                properties.put("remarksReporting", "true");
                return DriverManager.getConnection(url, properties);
            default:
                DialogUtil.showMsg("未支持数据库：" + config.getDbType());
                return null;
        }
    }

    public static String getConnectionUrl(DbConfig config) {
        DbType dbType = DbType.valueOf(config.getDbType());
        String url = dbType.getConnectionUrlPattern();
        if (dbType.equals(DbType.MySQL) || dbType.equals(DbType.MySQL_8)) {
            return  String.format(url, config.getHost(), config.getPort(), config.getSchema(), "false");
        } else if (dbType.equals(DbType.Oracle)) {
            return String.format(url, config.getHost(), config.getPort(), config.getSchema());
        }
        return "";
    }

    public static List<Table> getTables(DbConfig config) {
        DatabaseMetaData md;
        Statement statement;
        try (Connection conn = getConnection(config)) {
            md = conn.getMetaData();
            statement = conn.createStatement();
            switch (config.getDbType()) {
                case "MySQL":
                case "MySQL_8":
                    return MySql.getTables(md, statement, config.getSchema());
                case "Oracle":
                    return Oracle.getTables(md, statement, config.getSchema());
                default:
                    DialogUtil.showMsg("非法数据库类型  " + config.getDbType());
                    return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DialogUtil.showMsg("数据库连接失败，错误信息：" + e.getMessage());
            return null;
        }
    }


    public static List<Field> getTableFields(DbConfig dbConfig, String tableName) {
        DatabaseMetaData md;
        try (Connection conn = getConnection(dbConfig)) {
            md = conn.getMetaData();
            //TODO 新增数据库类型时，在此处拓展即可
            switch (dbConfig.getDbType()) {
                case "MySQL":
                case "MySQL_8":
                    return MySql.getTableFields(md, dbConfig.getSchema(), tableName);
                case "Oracle":
                    return Oracle.getTableFields(md, dbConfig.getSchema(), tableName);
                default:
//                    DialogUtil.showMsg("非法数据库类型  " + dbConfig.getDbType());
                    return null;
            }
        } catch (Exception e) {
//            DialogUtil.showMsg("获取表信息失败，错误信息：" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getPrimaryKey(DbConfig dbConfig, String tableName) {
        List<String> result = new ArrayList<>();
        try (Connection conn = getConnection(dbConfig)) {
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getPrimaryKeys(null, null, tableName);
            if (rs == null) {
                DialogUtil.showMsg("表".concat(tableName.concat("获取主键失败")));
            } else {
                while (rs.next()) {
                    String name = rs.getString("COLUMN_NAME");
                    // TODO 多主键时需要返回列表逐一判断
                    result.add(name);
                }
            }
        } catch (Exception ignored) {

        }
        return result;
    }

    public static void main(String[] args) {
        getTableFields(new DbConfig.Builder().dbType("MySQL").schema("evaluate").host("mshcc").username("root").port("3306").password("ab111403").build(), "prize");
    }
}
