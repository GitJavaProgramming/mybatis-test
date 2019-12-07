package org.pp.annotation.demo_sql;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {
    public static void main(String[] args) throws Exception {
        Member m = new Member();
        Class<?> clazz = m.getClass();
        String tableName = null;
        // 获取Member上的注解
        DBTable dbTable = clazz.getAnnotation(DBTable.class);
        if (dbTable != null) {
            tableName = dbTable.name();
        }
        if (tableName == null) { // 如果没有指定Table名称，使用类名
            tableName = clazz.getSimpleName().toUpperCase();
        }
        List<String> columnDefs = new ArrayList<String>();
        // 构造SQL创建列语句
        for (Field f : clazz.getDeclaredFields()) {  // 遍历类中声明的所有字段
            String columnName = null;
            // 获取当前字段上声明的所有注解
            Annotation[] anns = f.getDeclaredAnnotations();
            if (anns.length < 1) { // 当前字段上没有声明注解
                continue;
            }
            if (anns[0] instanceof SQLInteger) { // 注解数组第一个定义的是数据类型
                SQLInteger sInt = (SQLInteger) anns[0];
                if (sInt.name().length() < 1) {// 如果没有指定列名称，则使用字段名称
                    columnName = f.getName().toUpperCase();
                } else {
                    columnName = sInt.name();
                }
                columnDefs.add(columnName + " INT" + getConstraints(sInt.constraints()));
            }
            if (anns[0] instanceof SQLString) {
                SQLString sString = (SQLString) anns[0];
                if (sString.name().length() < 1) {// 如果没有指定列名称，则使用字段名称
                    columnName = f.getName().toUpperCase();
                } else {
                    columnName = sString.name();
                }
                columnDefs.add(columnName + " VARCHAR(" + sString.value() + ")" + getConstraints(sString.constraints()));
            }
        }
        StringBuilder createCommand = new StringBuilder("CREATE TABLE " + tableName + "(");
        for (String columnDef : columnDefs) {
            createCommand.append("\n " + columnDef + ",");
        }
        // 删除最后的","
        String tableCreate = createCommand.substring(0, createCommand.length() - 1) + ");";
        System.out.println("Table creation SQL for " + tableName + " is :\n" + tableCreate);
    }

    // 获取约束
    private static String getConstraints(Constraints con) {
        String constraints = "";
        if (!con.allowNull()) {
            constraints += " NOT NULL";
        }
        if (con.primaryKey()) {
            constraints += " PRIMARY KEY";
        }
        if (con.unique()) {
            constraints += " UNIQUE";
        }
        return constraints;
    }
}
