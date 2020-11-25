package com.test3;

import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 * Created by yuchen.wu on 2020-11-18
 */

public class MyDataSource implements DataSource {

    private static class InstanceHolder{
        private static MyDataSource INSTANCE = new MyDataSource();

        static {
            int capacity = CONNECTION_POOL.remainingCapacity();
            Map<Connection, Connection> connectionMap = new HashMap<>();
            String host = System.getProperty("host", "localhost");
            int port = Integer.parseInt(System.getProperty("port", "3306"));
            String username =  System.getProperty("user", "root");
            String password =  System.getProperty("pass", "root");
            for (int i = 0; i < capacity; i++) {
                try {
                    Connection connection = DriverManager.getConnection(String.format(CONNECTION_TEMPLATE, host, port, "test"),
                            username, password);
                    Connection myConnection = (Connection) MyConnection.createProxy(connection, INSTANCE);
                    connectionMap.put(myConnection, connection);
                    CONNECTION_POOL.add(myConnection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            Runtime.getRuntime().addShutdownHook(new Thread(()->{
                for (int i = 0; i < 10; i++) {
                    Connection connection;
                    try {
                        connection = CONNECTION_POOL.poll(1000, TimeUnit.SECONDS);
                        if (connection == null) {
                            continue;
                        }
                        Connection realConnection = connectionMap.get(connection);
                        if (realConnection == null) {
                            continue;
                        }
                        realConnection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("关闭资源");
            }));
        }
    }

    private static final String CONNECTION_TEMPLATE = "jdbc:mysql://%s:%s/%s?useSSL=false";
    private static final LinkedBlockingQueue<Connection> CONNECTION_POOL = new LinkedBlockingQueue<>(10);

    private MyDataSource(){
    }

    public static MyDataSource getInstance() {
        return InstanceHolder.INSTANCE;
    }


    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = CONNECTION_POOL.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(Thread.currentThread() + "获取连接" + connection + "，连接池剩余连接个数: " + CONNECTION_POOL.size());
        return connection;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getConnection();
    }

    public void releaseConnection(Connection connection) {
        CONNECTION_POOL.add(connection);
//        System.out.println(Thread.currentThread() + "释放连接" + connection + "，连接池剩余连接个数: " + CONNECTION_POOL.size());
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

}
