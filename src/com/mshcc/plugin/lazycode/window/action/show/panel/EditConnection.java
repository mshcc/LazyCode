package com.mshcc.plugin.lazycode.window.action.show.panel;

import com.intellij.openapi.ui.ComboBox;
import com.mshcc.plugin.lazycode.entity.DbConfig;
import com.mshcc.plugin.lazycode.entity.DbType;
import com.mshcc.plugin.lazycode.util.CheckUtil;
import com.mshcc.plugin.lazycode.window.dialog.DialogUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author mshcc
 * @date 2021/9/30 10:26
 * @Description 编辑链接展示窗口
 */
public class EditConnection extends JDialog {

    public static final EditConnection EDIT_CONNECTION_PANEL = new EditConnection();


    private JPanel panel;
    private JTextField connName;
    private JTextField schema;
    private JPasswordField password;
    private JTextField username;
    private JTextField port;
    private JTextField host;
    private ComboBox dbType;
    private JLabel schemaName;

    {
        dbType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DbType.Oracle.equals(dbType.getSelectedItem().toString())) {
                    schemaName.setText("服务名");
                } else {
                    schemaName.setText("数据库");
                }
            }
        });
    }

    public static JComponent getPanel() {
        return EDIT_CONNECTION_PANEL.panel;
    }

    public static DbConfig getDbConfig(boolean testConn) {
        if (checkParams(testConn)) {
            return new DbConfig.Builder()
                    .connName(EDIT_CONNECTION_PANEL.connName.getText())
                    .dbType(EDIT_CONNECTION_PANEL.dbType.getSelectedItem().toString())
                    .host(EDIT_CONNECTION_PANEL.host.getText())
                    .password(new String(EDIT_CONNECTION_PANEL.password.getPassword()))
                    .port(EDIT_CONNECTION_PANEL.port.getText())
                    .username(EDIT_CONNECTION_PANEL.username.getText())
                    .schema(EDIT_CONNECTION_PANEL.schema.getText())
                    .build();
        }
        return null;
    }

    private static boolean checkParams(boolean testConn) {
        System.out.println(EDIT_CONNECTION_PANEL.toString());
        if (!testConn && CheckUtil.isNull(EDIT_CONNECTION_PANEL.connName)) {
            DialogUtil.showMsg("连接名称不能为空");
            return false;
        }
        if (CheckUtil.isNull(EDIT_CONNECTION_PANEL.host)) {
            DialogUtil.showMsg("ip地址不能为空");
            return false;
        }
        if (CheckUtil.isNull(EDIT_CONNECTION_PANEL.port)) {
            DialogUtil.showMsg("端口号不能为空");
            return false;
        }
        if (CheckUtil.isNull(EDIT_CONNECTION_PANEL.username)) {
            DialogUtil.showMsg("用户名不能为空");
        }
        if (CheckUtil.isNull(EDIT_CONNECTION_PANEL.password)) {
            DialogUtil.showMsg("密码不能为空");
        }
        if (CheckUtil.isNull(EDIT_CONNECTION_PANEL.schema)) {
            DialogUtil.showMsg(EDIT_CONNECTION_PANEL.schemaName.getText().concat("不能为空"));
            return false;
        }
        return true;
    }

    public static void setDbConfig(DbConfig dbConfig) {
        EDIT_CONNECTION_PANEL.connName.setText(dbConfig.getConnName());
        EDIT_CONNECTION_PANEL.dbType.setSelectedItem(dbConfig.getDbType());
        EDIT_CONNECTION_PANEL.dbType.setEditable(false);
        EDIT_CONNECTION_PANEL.dbType.setEnabled(false);
        EDIT_CONNECTION_PANEL.host.setText(dbConfig.getHost());
        EDIT_CONNECTION_PANEL.password.setText(dbConfig.getPassword());
        EDIT_CONNECTION_PANEL.port.setText(dbConfig.getPort());
        EDIT_CONNECTION_PANEL.username.setText(dbConfig.getUsername());
        EDIT_CONNECTION_PANEL.schema.setText(dbConfig.getSchema());
    }
}
