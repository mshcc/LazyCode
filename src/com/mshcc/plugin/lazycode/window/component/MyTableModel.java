package com.mshcc.plugin.lazycode.window.component;

import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.mshcc.plugin.lazycode.complex.custom.Field;
import com.mshcc.plugin.lazycode.complex.custom.Template;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author mshcc
 */
public class MyTableModel extends AbstractTableModel {
    List<List<Object>> params = new ArrayList<>();
    String[] titles;
    public Integer inlineTemplate = 0;

    public MyTableModel(String[] titles) {
        this.titles = titles;
    }

    @Override
    public int getRowCount() {
        return params.size();
    }

    @Override
    public int getColumnCount() {
        return titles.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        return params.get(r).get(c);
    }

    @Override
    public String getColumnName(int column) {
        return titles[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class classz = null;
        try {
            classz = getValueAt(0, columnIndex).getClass();
        } catch (Exception e) {
            return TextFieldWithBrowseButton.class;
        }
        return classz;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        /*当选中为false或者对象为内置模板时，不允许编辑*/
        return (((boolean) getValueAt(rowIndex, 0)) && rowIndex >= inlineTemplate) || columnIndex == 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (params.size() <= rowIndex || params.get(rowIndex).size() <= columnIndex) {
            // 非法插入
            return;
        }
        if (columnIndex == 0 && aValue == null) {
            aValue = false;
        }
        params.get(rowIndex).set(columnIndex, aValue);
    }

    public void addRow(Object[] params) {
        List<Object> List = new ArrayList<>();
        Collections.addAll(List, params);
        this.params.add(List);
    }

    public void addRow(Object obj) {
        if (obj instanceof Template) {
            addRow(new Object[]{((Template) obj).enabled, ((Template) obj).name, ((Template) obj).path});
        } else if (obj instanceof Field) {
            addRow(new Object[]{((Field) obj).enabled, ((Field) obj).name, ((Field) obj).value});
        }
    }

    public void addRowList(List<?> objs) {
        if (objs.size() <= 0) {
            return;
        }
        if (objs.get(0) instanceof Template) {
            for (Object obj : objs) {
                addRow(obj);
            }
        } else if (objs.get(0) instanceof Field) {
            for (Object obj : objs) {
                addRow(obj);
            }
        }
    }

    public void removeRow(int rowIndex) {
        if (rowIndex > params.size() || rowIndex < inlineTemplate) {
            return;
        }
        params.remove(rowIndex);
    }

    public void clear() {
        params.clear();
    }

    public <T> List<T> getList(T param) {
        List<T> list = new ArrayList<>();
        if (param instanceof Template) {
            for (List<Object> objects : this.params) {
                list.add((T) new Template((String) objects.get(1), (String) objects.get(2), (boolean) objects.get(0)));
            }
        } else if (param instanceof Field) {
            for (List<Object> objects : this.params) {
                list.add((T) new Field((String) objects.get(1), (String) objects.get(2), (boolean) objects.get(0)));
            }
        }
        return list;
    }

}
