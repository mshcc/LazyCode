package com.mshcc.plugin.lazycode.util.db;
;

import com.mshcc.plugin.lazycode.entity.Field;
import com.mshcc.plugin.lazycode.entity.Table;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author mshcc
 * @Date 2021/9/24 15:18
 * @Description  新增的数据库支持需要实现下列方法 【无需继承，并将方法设置成static】
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public abstract class BaseDb {
    abstract String sql2Java(String sqlType);

    abstract List<Table> getTables(DatabaseMetaData md, ResultSet rs, String schemaName);

    abstract List<Field> getTableFields(DatabaseMetaData metaData, ResultSet rs, String schema, String tableName);

}
