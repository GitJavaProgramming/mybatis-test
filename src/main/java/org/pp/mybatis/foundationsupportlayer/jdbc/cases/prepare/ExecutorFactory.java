package org.pp.mybatis.foundationsupportlayer.jdbc.cases.prepare;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.pp.mybatis.foundationsupportlayer.jdbc.ConfigUtil;

import javax.sql.DataSource;

public class ExecutorFactory {

    private static Environment environment;

    private ExecutorFactory() {
    }

    public static BaseExecutor newExecutor() {
        if (environment == null) {
            // 读配置文件，获取数据源 这里直接用mybatis数据源测试
            // UnpooledDataSource使用DriverManager.getDrivers();获取classpath下已加载的driver
            // 如果pom中去掉唯一的mysql driver依赖 Client调用就会产生异常
            // DataSource实现 请参考jdbc规范
            DataSource dataSource = new UnpooledDataSource(ConfigUtil.getJdbcDriverClass(), ConfigUtil.getJdbcUrl(),
                    ConfigUtil.getJdbcUserName(), ConfigUtil.getJdbcPassWord());
            environment = new Environment("", dataSource);
        }
        return new SimpleExecutor(environment);
    }

//    private static final class Holder {
//        private static final ExecutorFactory EXECUTOR_FACTORY = new ExecutorFactory();
//    }
//
//    public static final ExecutorFactory getInstance() {
//        return Holder.EXECUTOR_FACTORY;
//    }
}
