package org.pp.mybatis.foundationsupportlayer.jdbc.cases.prepare;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 提供事务管理机制和基本数据库操作
 */
public abstract class BaseExecutor {

    protected JdbcTransaction transaction;

    private Environment environment;

    protected BaseExecutor(Environment environment) {
        this.environment = environment;
        if (this.transaction == null) {
            this.transaction = this.environment.getJdbcTransaction();
        }
    }

    public abstract <E> List<E> doQuery(String parameter) throws SQLException;

    public JdbcTransaction getTransaction() {
        return transaction;
    }

    protected Connection getConnection() throws SQLException {
        return transaction.getConnection();
    }
}
