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
 * @description 新增链接展示窗口
 */
public class AddConnection extends JDialog {

    public static final AddConnection CONNECTION_PANEL = new AddConnection();

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
                if (DbType.Oracle.equals(dbType.getItem().toString())) {
                    schemaName.setText("服务名");
                } else {
                    schemaName.setText("数据库");
                }
            }
        });
    }

    public static JComponent getPanel() {
        return CONNECTION_PANEL.panel;
    }

    public static DbConfig getDbConfig(boolean testConn) {
        if (checkParams(testConn)) {
            return new DbConfig.Builder()
                    .connName(CONNECTION_PANEL.connName.getText())
                    .dbType(CONNECTION_PANEL.dbType.getItem().toString())
                    .host(CONNECTION_PANEL.host.getText())
                    .password(new String(CONNECTION_PANEL.password.getPassword()))
                    .port(CONNECTION_PANEL.port.getText())
                    .username(CONNECTION_PANEL.username.getText())
                    .schema(CONNECTION_PANEL.schema.getText())
                    .build();
        }
        return null;
    }

    private static boolean checkParams(boolean testConn) {
        System.out.println(CONNECTION_PANEL.toString());
        if (!testConn && CheckUtil.isNull(CONNECTION_PANEL.connName)) {
            DialogUtil.showMsg("连接名称不能为空");
            return false;
        }
        if (CheckUtil.isNull(CONNECTION_PANEL.host)) {
            DialogUtil.showMsg("ip地址不能为空");
            return false;
        }
        if (CheckUtil.isNull(CONNECTION_PANEL.port)) {
            DialogUtil.showMsg("端口号不能为空");
            return false;
        }
        if (CheckUtil.isNull(CONNECTION_PANEL.username)) {
            DialogUtil.showMsg("用户名不能为空");
        }
        if (CheckUtil.isNull(CONNECTION_PANEL.password)) {
            DialogUtil.showMsg("密码不能为空");
        }
        if (CheckUtil.isNull(CONNECTION_PANEL.schema)) {
            DialogUtil.showMsg(CONNECTION_PANEL.schemaName.getText().concat("不能为空"));
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AddConnection{" +
                "  connName=" + connName.getText() +
                ", schema=" + schema.getText() +
                ", password=" + new String(password.getPassword()) +
                ", username=" + username.getText() +
                ", port=" + port.getText() +
                ", host=" + host.getText() +
                ", dbType=" + dbType.getItem() +
                ", schemaName=" + schemaName.getText() +
                '}';
    }
}
