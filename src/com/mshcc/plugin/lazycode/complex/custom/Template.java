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

    @Override
    public String toString() {
        return "Template{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    public Template() {
    }

    public Template(String name, String path, Boolean enabled) {
        this.name = name;
        this.path = path;
        this.enabled = enabled;
    }
}
