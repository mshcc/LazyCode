package com.mshcc.plugin.lazycode.complex.custom;

/**
 * @Author: mshcc
 * @Date: 2021/10/18  15:11
 * @Description:
 */
public class Field {
    public String name;
    public String value;
    public Boolean enabled;
    public Boolean editable;

    public Field() {
    }

    public Field(String name, String value, Boolean enabled, Boolean editable) {
        this.name = name;
        this.value = value;
        this.enabled = enabled;
        this.editable = editable;
    }
}
