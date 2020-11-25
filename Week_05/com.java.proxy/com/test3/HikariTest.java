package com.test3;

import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Created by yuchen.wu on 2020-11-18
 */

public class HikariTest {

    private static final String CONNECTION_TEMPLATE = "jdbc:mysql://%s:%s/%s?useSSL=false";
    private static final HikariDataSource ds;

    static {
        String host = System.getProperty("host", "localhost");
        int port = Integer.parseInt(System.getProperty("port", "3306"));
        String username =  System.getProperty("user", "root");
        String password =  System.getProperty("pass", "root");

        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setJdbcUrl(String.format(CONNECTION_TEMPLATE, host, port, "test"));
        ds = new HikariDataSource(config);
    }

    public static void main(String[] args) throws SQLException, InterruptedException {
        SqlTestUtil sqlTestUtil = SqlTestUtil.getInstance();
        sqlTestUtil.querySchoolTest(ds);
        sqlTestUtil.updateSchoolTest(ds);
        sqlTestUtil.deleteStudent(ds);
        sqlTestUtil.insertStudent(ds);
        sqlTestUtil.batchInsert(ds);
        sqlTestUtil.batchDelete(ds);
        //多线程问题，还未解决，最后发现线程池关不掉。还未 定位问题
        sqlTestUtil.batchQuerySchoolTest(ds);
    }


}
