package org.pp.mybatis.foundationsupportlayer.jdbc.cases.prepare;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Environment environment) {
        super(environment);
    }

    public <E> List<E> doQuery(String parameter) throws SQLException {
        Statement stmt = prepareStatement();
        boolean flag = stmt.execute(parameter);
        System.out.println("执行sql：" + flag);
        // 自己根据jdbc api 实现去吧~~~
        return new ArrayList<E>();
    }

    private Statement prepareStatement() throws SQLException {
        Statement stmt;
        Connection connection = getConnection();
        stmt = connection.createStatement();
        return stmt;
    }
}
