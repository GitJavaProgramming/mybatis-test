package org.pp.mybatis.foundationsupportlayer.jdbc.cases.prepare;

import javax.sql.DataSource;

/**
 * 提供数据源和事务管理
 */
public final class Environment {
    private final String id;
    private final JdbcTransaction jdbcTransaction;
    private final DataSource dataSource;

    public Environment(String id, DataSource dataSource) {
        this.id = id;
        this.dataSource = dataSource;
        this.jdbcTransaction = newTransaction(dataSource, null, false);
    }

    public String getId() {
        return this.id;
    }

    public JdbcTransaction getJdbcTransaction() {
        return jdbcTransaction;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }

    /**
     *  数据源创建JdbcTransaction实例
     * @param ds
     * @param level
     * @param autoCommit
     * @return
     */
    public JdbcTransaction newTransaction(DataSource ds, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(ds, level, autoCommit);
    }

}
