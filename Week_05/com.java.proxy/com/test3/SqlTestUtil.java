package com.test3;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.sql.DataSource;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * Created by yuchen.wu on 2020-11-25
 */

public class SqlTestUtil {

    public static SqlTestUtil getInstance() {
        return (SqlTestUtil) SqlTestUtilProxy.createProxy(SqlTestUtil.class);
    }

    private static class SqlTestUtilProxy implements MethodInterceptor {

        public static Object createProxy(final Class target) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(target);
            SqlTestUtilProxy sqlTestUtilProxy = new SqlTestUtilProxy();
            enhancer.setCallback(sqlTestUtilProxy);
            return enhancer.create();
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            long start = System.currentTimeMillis();
            // o 是 Bird$$EnhancerByCGLIB$$a77452a7@4dfa3a9d
            Object retVal = methodProxy.invokeSuper(o, objects);
            System.out.println(method.getName() + " cost : " + (System.currentTimeMillis() - start));
            return retVal;
        }
    }

    public  void batchQuerySchoolTest(DataSource dataSource) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(11);
        for (int i = 0; i < 11; i++) {
            executorService.execute(() -> {
                try {
                    querySchoolTest(dataSource);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

    public  void querySchoolTest(DataSource dataSource) throws SQLException, InterruptedException {
        long startTime = System.currentTimeMillis();
        Connection connection = dataSource.getConnection();
        Statement state = connection.createStatement();
        ResultSet resultSet = state.executeQuery("select * from school");
        while (resultSet.next()) {
//            System.out.println(
//                    Thread.currentThread() + " querySchoolTest--" + System.currentTimeMillis() + ":" + resultSet.getString(
//                            "name"));
        }
        resultSet.close();
        state.close();
        connection.close();
        System.out.println("querySchoolTest cost : "+ (System.currentTimeMillis() - startTime));
    }

    public  void updateSchoolTest(DataSource dataSource) throws SQLException, InterruptedException {
        Connection connection = dataSource.getConnection();
        Statement state = connection.createStatement();
        int result = state.executeUpdate("update school set `name` = 'geek1' where id = 1;");
//        System.out.println("updateSchoolTest--" + System.currentTimeMillis() + ":" + result);
        result = state.executeUpdate("update school set `name` = 'geek_school' where id = 1;");
//        System.out.println("updateSchoolTest--" +System.currentTimeMillis() + ":" + result);
        state.close();
        connection.close();
    }

    public  void deleteStudent(DataSource dataSource) throws SQLException, InterruptedException {
        Connection connection = dataSource.getConnection();
        Statement state = connection.createStatement();
        int result = state.executeUpdate("delete from student where id = 1;");
//        System.out.println("deleteStudent--" +System.currentTimeMillis() + ":" + result);
        state.close();
        connection.close();
    }

    public  void insertStudent(DataSource dataSource) throws SQLException, InterruptedException {
        Connection connection = dataSource.getConnection();
        Statement state = connection.createStatement();
        int result = state.executeUpdate("INSERT INTO student (`id`, `name`, `age`, `klass_id`) VALUES (1, 'wyc', 28, 1);");
//        System.out.println("insertStudent--" +System.currentTimeMillis() + ":" + result);
        state.close();
        connection.close();
    }

    public  void batchInsert(DataSource dataSource) throws InterruptedException, SQLException {
        Connection connection = dataSource.getConnection();
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
//        System.out.println("batchInsert--" +System.currentTimeMillis() );
        connection.close();
    }

    public  void batchDelete(DataSource dataSource) throws InterruptedException, SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM student WHERE id = ?;");
        for (int i = 3; i <= 4; i++) {
            ps.setInt(1, i);
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
//        System.out.println("batchDelete--" +System.currentTimeMillis());
        connection.close();
    }
}
