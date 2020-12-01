package com.wyc.work.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by yuchen.wu on 2020-11-18
 */
@Slf4j
public class MyDataSource implements DataSource {

    private LinkedBlockingQueue<Connection> connectionPool;
    private Map<Connection, Connection> connectionMap;

    public static MyDataSource generateMyDataSource(String driver, String url, String username, String password) {
        MyDataSource myDataSource = new MyDataSource();
        myDataSource.connectionPool  = new LinkedBlockingQueue<>(10);
        myDataSource.connectionMap = new ConcurrentHashMap<>();
        int capacity = myDataSource.connectionPool.remainingCapacity();
        try {
            for (int i = 0; i < capacity; i++) {
                Connection connection = DriverManager.getConnection(url, username, password);
                Connection myConnection = (Connection) MyConnection.createProxy(connection, myDataSource);
                myDataSource.connectionMap.put(myConnection, connection);
                myDataSource.connectionPool.add(myConnection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myDataSource;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionPool.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void destroy() throws Exception {
        for (int i = 0; i < 10; i++) {
            Connection connection;
            try {
                connection = this.connectionPool.poll(1000, TimeUnit.SECONDS);
                if (connection == null) {
                    continue;
                }
                Connection realConnection = this.connectionMap.get(connection);
                if (realConnection == null) {
                    continue;
                }
                realConnection.close();
            } catch (Exception e) {
                log.error("realConnection close error!", e);
            }
        }
        log.info("all connection closed");
    }


    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getConnection();
    }

    public void releaseConnection(Connection connection) {
        connectionPool.add(connection);
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
