package com.mshcc.plugin.lazycode.entity;

/**
 * @author mshcc
 */
public class Field {
    private String fieldName;
    private String upperFieldName;
    private String fieldType;
    private String fieldNameInDb;
    private String remark;
    private String notNull;
    // "YES"  ||  "NO"   ||  ""
    private String autoincrement;
    private Integer maxSize;
    private boolean tableId;
    private boolean pattern;
    private String name;
    private boolean primaryKey;
    private boolean flag = false;

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPattern() {
        return pattern;
    }

    public void setPattern(boolean pattern) {
        this.pattern = pattern;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public boolean isTableId() {
        return tableId;
    }

    public void setTableId(boolean tableId) {
        this.tableId = tableId;
    }

    public String getAutoincrement() {
        return autoincrement;
    }

    public void setAutoincrement(String autoincrement) {
        this.autoincrement = autoincrement;
    }

    public String getNotNull() {
        return notNull;
    }

    public void setNotNull(String notNull) {
        this.notNull = notNull;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Field() {
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getUpperFieldName() {
        return upperFieldName;
    }

    public void setUpperFieldName(String upperFieldName) {
        this.upperFieldName = upperFieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldNameInDb() {
        return fieldNameInDb;
    }

    public void setFieldNameInDb(String fieldNameInDb) {
        this.fieldNameInDb = fieldNameInDb;
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldName='" + fieldName + '\'' +
                ", upperFieldName='" + upperFieldName + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", fieldNameInDb='" + fieldNameInDb + '\'' +
                ", remark='" + remark + '\'' +
                ", notNull='" + notNull + '\'' +
                ", autoincrement='" + autoincrement + '\'' +
                ", maxSize=" + maxSize +
                ", tableId=" + tableId +
                ", pattern=" + pattern +
                ", name='" + name + '\'' +
                ", flag=" + flag +
                '}';
    }
}
