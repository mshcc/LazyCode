package com.mshcc.plugin.lazycode.window.action.show.panel;

import com.mshcc.plugin.lazycode.complex.SettingState;
import com.mshcc.plugin.lazycode.complex.custom.Field;
import com.mshcc.plugin.lazycode.complex.custom.Template;
import com.mshcc.plugin.lazycode.entity.SettingConfig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @Author: mshcc
 * @Date: 2021/10/18  13:42
 * @Description:
 */
public class Setting {

    public static final Setting SETTING = new Setting();

    private JPanel panel;
    private JButton customField;
    private JButton customTemplate;
    private JTextArea text;

    public JComponent getComponent() {
        return SETTING.panel;
    }

    public static void init() {
        SETTING.customField.addActionListener(actionEvent -> {
        });
    }

}
