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
    private String autoincrement;
    private boolean primaryKey;

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
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
                ", autoincrement='" + autoincrement +
                '}';
    }
}
