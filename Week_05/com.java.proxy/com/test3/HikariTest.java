package com.test3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        querySchoolTest();
        updateSchoolTest();
        deleteStudent();
        insertStudent();
        batchInsert();
        batchDelete();
        //多线程问题，还未解决，最后发现线程池关不掉。还未 定位问题
        batchQuerySchoolTest();
    }

    private static void batchQuerySchoolTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(11);
        for (int i = 0; i < 11; i++) {
            executorService.submit(() -> {
                try {
                    querySchoolTest();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

    private static void querySchoolTest() throws InterruptedException, SQLException {
        Connection connection = ds.getConnection();
        Statement state = connection.createStatement();
        ResultSet resultSet = state.executeQuery("select * from school");
        while (resultSet.next()) {
            System.out.println(System.currentTimeMillis() + ":" + resultSet.getString("name"));
        }
        Thread.sleep(1000);
        resultSet.close();
        state.close();
        connection.close();
    }

    private static void updateSchoolTest() throws SQLException, InterruptedException {
        Connection connection = ds.getConnection();
        Statement state = connection.createStatement();
        int result = state.executeUpdate("update school set `name` = 'geek1' where id = 1;");
        System.out.println(System.currentTimeMillis() + ":" + result);
        result = state.executeUpdate("update school set `name` = 'geek_school' where id = 1;");
        System.out.println(System.currentTimeMillis() + ":" + result);
        state.close();
        connection.close();
    }

    private static void deleteStudent() throws SQLException, InterruptedException {
        Connection connection = ds.getConnection();
        Statement state = connection.createStatement();
        int result = state.executeUpdate("delete from student where id = 1;");
        System.out.println(System.currentTimeMillis() + ":" + result);
        state.close();
        connection.close();
    }

    private static void insertStudent() throws SQLException, InterruptedException {
        Connection connection = ds.getConnection();
        Statement state = connection.createStatement();
        int result = state.executeUpdate("INSERT INTO student (`id`, `name`, `age`, `klass_id`) VALUES (1, 'wyc', 28, 1);");
        System.out.println(System.currentTimeMillis() + ":" + result);
        state.close();
        connection.close();
    }

    private static void batchInsert() throws InterruptedException, SQLException {
        Connection connection = ds.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO student (`id`, `name`, `age`, `klass_id`) "
                + "VALUES (?, ?, ?, ?)");
        for (int i = 3; i <= 4; i++) {
            ps.setInt(1, i);
            ps.setString(2, "name" + i);
            ps.setInt(3, i);
            ps.setInt(4, 1);
            ps.addBatch();
        }
        ps.executeBatch();
        ps.closeOnCompletion();
        connection.close();
    }

    private static void batchDelete() throws InterruptedException, SQLException {
        Connection connection = ds.getConnection();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM student WHERE id = ?;");
        for (int i = 3; i <= 4; i++) {
            ps.setInt(1, i);
            ps.addBatch();
        }
        ps.executeBatch();
        ps.closeOnCompletion();
        connection.close();
    }

}
