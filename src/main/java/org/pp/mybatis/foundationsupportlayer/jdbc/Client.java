package org.pp.mybatis.foundationsupportlayer.jdbc;

import org.pp.mybatis.foundationsupportlayer.jdbc.cases.prepare.BaseExecutor;
import org.pp.mybatis.foundationsupportlayer.jdbc.cases.prepare.ExecutorFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class Client {
    public static void main(String[] args) throws SQLException {
        BaseExecutor executor = ExecutorFactory.newExecutor();
        executor.doQuery("1");
        Connection connection = executor.getTransaction().getConnection();
        DatabaseMetaData metaData = connection.getMetaData();
        System.out.println("driverName: " + metaData.getDriverName());
        System.out.println("DriverVersion: " + metaData.getDriverVersion());
    }
}
