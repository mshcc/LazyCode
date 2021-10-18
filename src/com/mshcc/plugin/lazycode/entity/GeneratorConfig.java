package com.mshcc.plugin.lazycode.entity;

/**
 * @author mshcc
 * @Date 2021/7/26 19:52
 * @Description TODO
 */
public class GeneratorConfig {

    private final String table;
    private final String entity;
    private final String codeDir;

    public String getTable() {
        return table;
    }

    public String getEntity() {
        return entity;
    }

    public String getCodeDir() {
        return codeDir;
    }


    @Override
    public String toString() {
        return "GeneratorConfig{" +
                "table='" + table + '\'' +
                ", entity='" + entity + '\'' +
                ", codeDir='" + codeDir + '\'' +
                '}';
    }

    private GeneratorConfig(Builder builder) {
        this.table = builder.table;
        this.entity = builder.entity;
        this.codeDir = builder.codeDir;
    }

    public static class Builder {
        private String table;
        private String entity;
        private String codeDir;

        public Builder table(String table) {
            this.table = table;
            return this;
        }

        public Builder entity(String entity) {
            this.entity = entity;
            return this;
        }

        public Builder codeDir(String codeDir) {
            this.codeDir = codeDir;
            return this;
        }

        public GeneratorConfig build() {
            return new GeneratorConfig(this);
        }
    }
}
