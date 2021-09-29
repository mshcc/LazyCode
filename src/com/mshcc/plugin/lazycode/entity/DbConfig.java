package com.mshcc.plugin.lazycode.entity;



import java.util.Objects;
/*
 * @Author msh
 * @Date 2021/9/29 上午11:33
 * @Description 数据库链接所需要的信息
 */
public class DbConfig {

    /**
     * 链接保存名称
     */
    private final String connName;
    /**
     * 数据库类型
     */
    private final String dbType;
    /**
     * ip地址 | url
     */
    private final String host;
    /**
     * 链接端口
     */
    private final String port;
    /**
     * 用户名
     */
    private final String username;
    /**
     * 密码
     */
    private final String password;
    /**
     * 数据库名 | 模式(oracle)
     */
    private final String schema;

    public String getDbType() {
        return dbType;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConnName() {
        return connName;
    }

    public String getSchema() {
        return schema;
    }

    @Override
    public String toString() {
        return "DatabaseConfig{" +
                "connName='" + connName + '\'' +
                ", dbType='" + dbType + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", schema='" + schema + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DbConfig config = (DbConfig) o;
        return Objects.equals(dbType, config.dbType) &&
                Objects.equals(host, config.host) &&
                Objects.equals(port, config.port) &&
                Objects.equals(username, config.username) &&
                Objects.equals(password, config.password) &&
                Objects.equals(schema, config.schema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dbType, host, port, username, password, schema);
    }

    private DbConfig(Builder builder) {
        this.connName = builder.connName;
        this.dbType = builder.dbType;
        this.host = builder.host;
        this.port = builder.port;
        this.username = builder.username;
        this.password = builder.password;
        this.schema = builder.schema;
    }

    public static class Builder {
        private String connName;
        private String dbType;
        private String host;
        private String port;
        private String username;
        private String password;
        private String schema;

        public Builder connName(String connName) {
            this.connName = connName;
            return this;
        }

        public Builder dbType(String dbType) {
            this.dbType = dbType;
            return this;
        }

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(String port) {
            this.port = port;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder schema(String schema) {
            this.schema = schema;
            return this;
        }

        public DbConfig build() {
            return new DbConfig(this);
        }
    }
}
