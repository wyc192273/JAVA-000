package com.wyc.work.datasource;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

/**
 * Created by yuchen.wu on 2020-12-02
 */

public class DataSourceUtil {
    private static final String HOST = "localhost";

    private static final int PORT = 3306;

    private static final String USER_NAME = "root";

    private static final String PASSWORD = "root";

    public static DataSource createDataSource(final String dataSourceName) {
        HikariDataSource result = new HikariDataSource();
        result.setDriverClassName("com.mysql.jdbc.Driver");
        result.setJdbcUrl(String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8", HOST, PORT, dataSourceName));
        result.setUsername(USER_NAME);
        result.setPassword(PASSWORD);
        result.setMaximumPoolSize(5);
        return result;
    }
}
