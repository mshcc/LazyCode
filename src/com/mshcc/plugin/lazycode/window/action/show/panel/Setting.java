package com.mshcc.plugin.lazycode.window.action.show.panel;

import javax.swing.*;

/**
 * @Author: mshcc
 * @Date: 2021/10/18  13:42
 * @Description:
 */
public class Setting {
    private JPanel panel;
    private JButton 自定义字段Button;
    private JButton 自定义模板Button;
    private JTextArea 自定义字段中设置模板中可插入的值模板只支持FreeMarker模板语言请自行学习TextArea;

    public JComponent getComponent(){
        return panel;
    }

}
