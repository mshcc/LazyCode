package com.mshcc.plugin.lazycode.entity;


public class Table {
    private String name;
    private String remarks;

    public Table() {
    }

    @Override
    public String toString() {
        if ("".equals(remarks)) {
            return name;
        }
        return name + "     \t[" + remarks + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Table(String name, String remarks) {
        if (remarks == null) {
            remarks = "";
        }
        this.name = name;
        this.remarks = remarks;
    }

}
