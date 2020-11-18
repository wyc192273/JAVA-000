package com.test3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yuchen.wu on 2020-11-18
 */

public class DataSourceDemo {

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

    private static void batchQuerySchoolTest() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(11);
        for (int i = 0; i < 11; i++) {
            executorService.execute(() -> {
                try {
                    querySchoolTest();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

    private static void querySchoolTest() throws SQLException, InterruptedException {
        MyDataSource myDataSource = MyDataSource.getInstance();
        Connection connection = myDataSource.getConnection();
        Statement state = connection.createStatement();
        ResultSet resultSet = state.executeQuery("select * from school");
        Thread.sleep(1000);
        while (resultSet.next()) {
            System.out.println(Thread.currentThread() + " querySchoolTest--" + System.currentTimeMillis() + ":" + resultSet.getString("name"));
        }
        resultSet.close();
        state.close();
        connection.close();
    }

    private static void updateSchoolTest() throws SQLException, InterruptedException {
        MyDataSource myDataSource = MyDataSource.getInstance();
        Connection connection = myDataSource.getConnection();
        Statement state = connection.createStatement();
        int result = state.executeUpdate("update school set `name` = 'geek1' where id = 1;");
        System.out.println("updateSchoolTest--" + System.currentTimeMillis() + ":" + result);
        result = state.executeUpdate("update school set `name` = 'geek_school' where id = 1;");
        System.out.println("updateSchoolTest--" +System.currentTimeMillis() + ":" + result);
        state.close();
        connection.close();
    }

    private static void deleteStudent() throws SQLException, InterruptedException {
        MyDataSource myDataSource = MyDataSource.getInstance();
        Connection connection = myDataSource.getConnection();
        Statement state = connection.createStatement();
        int result = state.executeUpdate("delete from student where id = 1;");
        System.out.println("deleteStudent--" +System.currentTimeMillis() + ":" + result);
        state.close();
        connection.close();
    }

    private static void insertStudent() throws SQLException, InterruptedException {
        MyDataSource myDataSource = MyDataSource.getInstance();
        Connection connection = myDataSource.getConnection();
        Statement state = connection.createStatement();
        int result = state.executeUpdate("INSERT INTO student (`id`, `name`, `age`, `klass_id`) VALUES (1, 'wyc', 28, 1);");
        System.out.println("insertStudent--" +System.currentTimeMillis() + ":" + result);
        state.close();
        connection.close();
    }

    private static void batchInsert() throws InterruptedException, SQLException {
        MyDataSource myDataSource = MyDataSource.getInstance();
        Connection connection = myDataSource.getConnection();
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
        ps.close();
        System.out.println("batchInsert--" +System.currentTimeMillis() );
        connection.close();
    }

    private static void batchDelete() throws InterruptedException, SQLException {
        MyDataSource myDataSource = MyDataSource.getInstance();
        Connection connection = myDataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM student WHERE id = ?;");
        for (int i = 3; i <= 4; i++) {
            ps.setInt(1, i);
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
        System.out.println("batchDelete--" +System.currentTimeMillis());
        connection.close();
    }

}
