package com.mshcc.plugin.lazycode.window.component;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: mshcc
 * @Date: 2021/10/18  10:15
 * @Description:
 */


public class JCheckBoxList extends JCheckBox implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        this.setText(value.toString());
        setBackground(list.getBackground());
//        setIcon(new ImageIcon(this.getClass().getResource("/icons/table.png")));
        this.setSelected(isSelected);
        return this;
    }
}
