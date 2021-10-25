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
public class Oracle {
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
        ResultSet rs = md.getTables(null, "c##msh", "%", null);

        List<Table> tables = new ArrayList<>();
        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME");
            System.out.println("tableName = " + tableName);
            ResultSet resultSet = statement.executeQuery("SELECT COMMENTS FROM ALL_TAB_COMMENTS  WHERE  OWNER = '" + "c##msh" + "' AND TABLE_NAME = '" + tableName + "'");
            String remarks = "";
            while (resultSet.next()) {
                remarks = resultSet.getString(1);
            }
            tables.add(new Table(tableName, remarks));
        }
        rs.close();
        return tables;
    }


    // 见 https://docs.oracle.com/en/database/oracle/oracle-database/19/jjdbc/accessing-and-manipulating-Oracle-data.html
    public static String sql2Java(String sqlType, int columnSize, Integer columnSize1) {
        switch (sqlType) {
            case "NUMBER":
                if (columnSize1 < 0) {
                    switch (columnSize) {
                        case 1:
                            return "Boolean";
                        case 2:
                            return "byte";
                        case 3:
                        case 4:
                            return "Short";
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                            return "Integer";
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                            return "Long";
                        default:
                            return "java.math.BigDecimal";
                    }
                } else {
                    return columnSize + columnSize1 < 17 ? "Double" : "java.math.BigDecimal";
                }
            case "VARCHAR2":
            case "CHAR":
            case "LONG":
            case "NCHAR":
                return "String";
            case "RAW":
            case "LONGRAW":
                return "byte[]";
            case "DATE":
                return "java.sql.DATE";
            case "TIMESTAMP":
            case "TIMESTAMP WITH TIME ZONE":
            case "TIMESTAMP WITH LOCAL TIME ZONE":
                return "javal.sql.Timestamp";
            case "BLOB":
                return "java.sql.Blob";
            case "Clob":
                return "java.sql.Clob";
            case "ROWID":
                return "java.sql.RowId";
            case "NCLOB":
                return "java.sql.NClob";
            case "REF CURSOR":
                return "java.sql.ResultSet";
            default:
                return "unKnow";
        }
    }

    public static void main(String[] args) {

    }
}
