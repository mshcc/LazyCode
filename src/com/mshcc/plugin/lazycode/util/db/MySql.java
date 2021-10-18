package com.mshcc.plugin.lazycode.util.db;


import com.mshcc.plugin.lazycode.entity.Field;
import com.mshcc.plugin.lazycode.entity.Table;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mshcc
 * @Date 2021/9/24 14:54
 * @Description TODO
 */
public class MySql {
    public static List<Field> getTableFields(DatabaseMetaData metaData, String schema, String tableName) throws SQLException {
        ResultSet rs = metaData.getColumns(schema, null, tableName, null);
        List<Field> columns = new ArrayList<>();
        while (rs.next()) {
            System.out.println("rs.getString(\"TABLE_CAT\") = " + rs.getString("TABLE_CAT"));
            System.out.println("rs.getString(\"TABLE_SCHEM\") = " + rs.getString("TABLE_SCHEM"));
            System.out.println("rs.getString(\"TABLE_NAME\") = " + rs.getString("TABLE_NAME"));
            System.out.println("rs.getString(\"COLUMN_NAME\") = " + rs.getString("COLUMN_NAME"));
            System.out.println("rs.getString(\"DATA_TYPE\") = " + rs.getString("DATA_TYPE"));
            System.out.println("rs.getString(\"TYPE_NAME\") = " + rs.getString("TYPE_NAME"));
            System.out.println("rs.getString(\"COLUMN_SIZE\") = " + rs.getString("COLUMN_SIZE"));
            System.out.println("rs.getString(\"BUFFER_LENGTH\") = " + rs.getString("BUFFER_LENGTH"));
            System.out.println("rs.getString(\"DECIMAL_DIGITS\") = " + rs.getString("DECIMAL_DIGITS"));
            System.out.println("rs.getString(\"NUM_PREC_RADIX\") = " + rs.getString("NUM_PREC_RADIX"));
            System.out.println("rs.getString(\"NULLABLE\") = " + rs.getString("NULLABLE"));
            System.out.println("rs.getString(\"REMARKS\") = " + rs.getString("REMARKS"));
            System.out.println("rs.getString(\"COLUMN_DEF\") = " + rs.getString("COLUMN_DEF"));
            System.out.println("rs.getString(\"SQL_DATA_TYPE\") = " + rs.getString("SQL_DATA_TYPE"));
            System.out.println("rs.getString(\"SQL_DATETIME_SUB\") = " + rs.getString("SQL_DATETIME_SUB"));
            System.out.println("rs.getString(\"CHAR_OCTET_LENGTH\") = " + rs.getString("CHAR_OCTET_LENGTH"));
            System.out.println("rs.getString(\"ORDINAL_POSITION\") = " + rs.getString("ORDINAL_POSITION"));
            System.out.println("rs.getString(\"IS_NULLABLE\") = " + rs.getString("IS_NULLABLE"));
            System.out.println("rs.getString(\"IS_AUTOINCREMENT\") = " + rs.getString("IS_AUTOINCREMENT"));
            Field field = new Field();
            columns.add(field);
        }
        rs.close();
        return columns;
    }


    public static List<Table> getTables(DatabaseMetaData md, Statement statement, String schemaName) throws SQLException {
        ResultSet rs = md.getTables(schemaName, null, "%", new String[]{"TABLE", "VIEW"});
        List<Table> tables = new ArrayList<>();
        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME");
            ResultSet resultSet = statement.executeQuery("SELECT TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '" + schemaName + "' AND TABLE_NAME = '" + tableName + "'");
            String remarks = "";
            while (resultSet.next()) {
                remarks = resultSet.getString(1);
            }
            tables.add(new Table(tableName, remarks));
        }
        rs.close();
        return tables;
    }

    public static String sql2Java(String sqlType) {
        if (sqlType == null || sqlType.trim().length() == 0) {
            return "unKnow";
        }
        sqlType = sqlType.toLowerCase();
        sqlType = sqlType.split(" ")[0];
        switch (sqlType) {
            case "nvarchar":
            case "json":
            case "char":
            case "varchar":
            case "text":
            case "nchar":
            case "longtext":
                return "String";
            case "blob":
            case "longblob":
            case "image":
                return "byte[]";
            case "integer":
            case "id":
            case "bigint":
                return "Long";
            case "tinyint":
            case "smallint":
            case "mediumint":
            case "int":
                return "Integer";
            case "bit":
            case "boolean":
                return "Boolean";
            case "float":
                return "Fload";
            case "double":
            case "money":
            case "smallmoney":
                return "Double";
            case "decimal":
            case "real":
            case "numeric":
                return "java.math.BigDecimal";
            case "date":
            case "datetime":
            case "year":
                return "java.util.Date";
            case "time":
                return "java.sql.Time";
            case "timestamp":
                return "java.sql.Timestamp";
            default:
                return "unKnow";
        }
    }

}
