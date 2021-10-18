package com.mshcc.plugin.lazycode.util;

import com.intellij.openapi.ui.TextFieldWithBrowseButton;

import javax.swing.*;

/**
 * @author mshcc
 * @Date 2021/9/30 10:32
 * @Description TODO
 */
public class CheckUtil {
    public static boolean isNull(JTextField field) {
        if (field instanceof JPasswordField) {
            return ((JPasswordField) field).getPassword().length == 0;
        }
        return field.getText() == null || "".equals(field.getText());
    }

    public static boolean notNull(JTextField field) {
        return !isNull(field);
    }

    public static boolean isNull(TextFieldWithBrowseButton field) {
        return "".equals(field.getText());
    }
}
