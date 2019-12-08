package org.pp.mybatis.foundationsupportlayer.jdbc;

import java.util.ResourceBundle;

public final class ConfigUtil {
    private static final ResourceBundle config = ResourceBundle.getBundle("jdbc");

    public static String getJdbcDriverClass() {
        return config.getString("jdbc.driverClass");
    }

    public static String getJdbcUrl() {
        return config.getString("jdbc.url");
    }

    public static String getJdbcUserName() {
        return config.getString("jdbc.username");
    }

    public static String getJdbcPassWord() {
        return config.getString("jdbc.password");
    }
}