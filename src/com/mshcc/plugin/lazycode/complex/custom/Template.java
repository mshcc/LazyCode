package com.mshcc.plugin.lazycode.complex.custom;

/**
 * @Author: mshcc
 * @Date: 2021/10/18  15:11
 * @Description:
 */
public class Template {
    public String name;
    public String path;
    public Boolean enabled;
    public Boolean editable;

    public Template() {
    }

    public Template(String name, String path, Boolean enabled, Boolean editable) {
        this.name = name;
        this.path = path;
        this.enabled = enabled;
        this.editable = editable;
    }
}
