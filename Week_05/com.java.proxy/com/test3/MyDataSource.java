package com.test3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by yuchen.wu on 2020-11-18
 */

public class MyDataSource {

    private static class InstanceHolder{
        private static MyDataSource INSTANCE = new MyDataSource();

        static {
            int capacity = CONNECTION_POOL.remainingCapacity();
            Map<Connection, Connection> connectionMap = new HashMap<>();
            for (int i = 0; i < capacity; i++) {
                try {
                    String host = System.getProperty("host", "localhost");
                    int port = Integer.parseInt(System.getProperty("port", "3306"));
                    String username =  System.getProperty("user", "root");
                    String password =  System.getProperty("pass", "root");
                    Connection connection = DriverManager.getConnection(String.format(CONNECTION_TEMPLATE, host, port, "test"),
                            username, password);
                    Connection connectionProxy = (Connection) MyConnectionProxy.createProxy(connection, INSTANCE);
                    connectionMap.put(connectionProxy, connection);
                    CONNECTION_POOL.add(connectionProxy);
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


    public Connection getConnection() throws InterruptedException {
        Connection connection = CONNECTION_POOL.take();
        System.out.println(Thread.currentThread() + "获取连接" + connection + "，连接池剩余连接个数: " + CONNECTION_POOL.size());
        return connection;
    }

    public void releaseConnection(Connection connection) {
        CONNECTION_POOL.add(connection);
        System.out.println(Thread.currentThread() + "释放连接" + connection + "，连接池剩余连接个数: " + CONNECTION_POOL.size());
    }

    private static class MyConnectionProxy{

        public static Object createProxy(Connection target, MyDataSource myDataSource) {
            return Proxy.newProxyInstance(MyConnectionProxy.class.getClassLoader(), target.getClass().getInterfaces(),
                    new InternalInvocationHandler(target, myDataSource));
        }

        private static class InternalInvocationHandler implements InvocationHandler {

            private MyDataSource myDataSource;
            private Connection target;

            public InternalInvocationHandler(Connection target, MyDataSource myDataSource) {
                this.myDataSource = myDataSource;
                this.target = target;
            }

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if (method.getName().equals("close")) {
                    System.out.println(Thread.currentThread() + "in invoke");
                    myDataSource.releaseConnection(target);
                    return null;
                } else {
                    return method.invoke(target, args);
                }
            }
        }
    }

}
