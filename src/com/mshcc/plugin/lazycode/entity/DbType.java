package com.mshcc.plugin.lazycode.entity;

/*
 * @Author msh
 * @Date 2021/9/29 上午11:38
 * @Description 数据库枚举类型
 */
public enum DbType {
//    PostgreSQL("PostgreSQL", "org.postgresql.Driver", "jdbc:postgresql://%s:%s/%s"),
//    SQL_Server("SQL_Server", "com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://%s:%s;databaseName=%s"),

    MySQL("MySQL", "com.mysql.jdbc.Driver", "jdbc:mysql://%s:%s/%s?useUnicode=true&serverTimezone=GMT%2B8"),
    MySQL_8("MySQL_8", "com.mysql.cj.jdbc.Driver", "jdbc:mysql://%s:%s/%s?useUnicode=true&serverTimezone=GMT%2B8"),
    Oracle("Oracle", "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@//%s:%s/%s");

    private final String driverClass;
    private final String connectionUrlPattern;
    private final String dbName;

    DbType(String dbName, String driverClass, String connectionUrlPattern) {
        this.dbName = dbName;
        this.driverClass = driverClass;
        this.connectionUrlPattern = connectionUrlPattern;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getConnectionUrlPattern() {
        return connectionUrlPattern;
    }

    public boolean equals(String dbType) {
        return this.dbName.equals(dbType);
    }

    /**
     * @param config 数据库链接信息
     * @return 数据库链接url
     */
    public String getConnectionUrl(DbConfig config) {
        return String.format(this.connectionUrlPattern, config.getHost(), config.getPort(), config.getSchema());
    }
}