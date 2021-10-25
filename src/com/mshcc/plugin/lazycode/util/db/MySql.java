package com.mshcc.plugin.lazycode.util.db;


import com.mshcc.plugin.lazycode.entity.Field;
import com.mshcc.plugin.lazycode.entity.Table;
import com.mshcc.plugin.lazycode.util.StringUtil;

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
            /*以下注释为预扩展内容，如有需要，打开注释自行编译*/
            /*目录名称【所在数据库名称】*/
//            String tableCat = rs.getString("TABLE_CAT");
            /*表架构名称*/
//            String tableSchem = rs.getString("TABLE_SCHEM");
            /*表名称 即传入参数tableName*/
//            String tableName1 = rs.getString("TABLE_NAME");
            /*数据类型 作用不明，自行扩展*/
//            String dataType = rs.getString("DATA_TYPE");
            /*列精度*/
//            String columnSize = rs.getString("COLUMN_SIZE");
            /*数据的传输大小*/
//            String bufferLength = rs.getString("BUFFER_LENGTH");
            /*列的小数位数*/
//            String decimalDigits = rs.getString("DECIMAL_DIGITS");
            /*列的基数*/
//            String numPrecRadix = rs.getString("NUM_PREC_RADIX");
            /*是否可以为null*/
//            String nullable = rs.getString("NULLABLE");
            /*列默认值*/
//            String columnDef = rs.getString("COLUMN_DEF");
            /*SQL 数据类型在描述符的 TYPE 字段中显示的值*/
//            String sqlDataType = rs.getString("SQL_DATA_TYPE");
            /*datetime 及 SQL-92 interval 数据类型的子类型代码*/
//            String sqlDatetimeSub = rs.getString("SQL_DATETIME_SUB");
            /*列中的最大字节数*/
//            String charOctetLength = rs.getString("CHAR_OCTET_LENGTH");
            /*列在表中的索引*/
//            String ordinalPosition = rs.getString("ORDINAL_POSITION");

            /*列名称*/
            String columnName = rs.getString("COLUMN_NAME");
            /*数据类型名称*/
            String typeName = rs.getString("TYPE_NAME");
            String columnType = sql2Java(typeName);
            /*注释*/
            String remarks = rs.getString("REMARKS");
            /*是否允许为null*/
            String isNullable = rs.getString("IS_NULLABLE");
            /*是否为自当递增*/
            String isAutoincrement = rs.getString("IS_AUTOINCREMENT");
            Field field = new Field();
            field.setFieldNameInDb(columnName);
            field.setFieldName(StringUtil.fieldCamelStyle(columnName));
            field.setFieldType(sql2Java(columnType));
            field.setAutoincrement(isAutoincrement);
            field.setRemark(remarks);
            field.setNotNull(isNullable);
            field.setUpperFieldName(StringUtil.toCamelStyle(columnName));
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
            ResultSet resultSet = statement.executeQuery(
                    "SELECT TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '"
                            + schemaName + "' AND TABLE_NAME = '" + tableName + "'");
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
