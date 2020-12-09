package com.wyc.sql.tool.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.zaxxer.hikari.HikariDataSource;

/**
 * Created by yuchen.wu on 2020-12-02
 */

public class DataSourceUtil {

    public static DataSource createDataSource(String host, int port, String user, String password, final String dataSourceName) {
        HikariDataSource result = new HikariDataSource();
        result.setDriverClassName("com.mysql.jdbc.Driver");
        result.setJdbcUrl(String.format(
                "jdbc:mysql://%s:%s/%s?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8&rewriteBatchedStatements=true",
                host, port, dataSourceName));
        result.setUsername(user);
        result.setPassword(password);
        result.setMaximumPoolSize(5);
        return result;
    }


}
