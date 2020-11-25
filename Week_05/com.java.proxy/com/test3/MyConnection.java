package com.test3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * Created by yuchen.wu on 2020-11-25
 */

public class MyConnection {

    public static Object createProxy(Connection target, MyDataSource myDataSource) {
        return Proxy.newProxyInstance(MyConnection.class.getClassLoader(), new Class[]{Connection.class},
                new MyConnection.InternalInvocationHandler(target, myDataSource));
    }

    private static class InternalInvocationHandler implements InvocationHandler {

        private MyDataSource myDataSource;
        private Connection realConnection;

        public InternalInvocationHandler(Connection connection, MyDataSource myDataSource) {
            this.myDataSource = myDataSource;
            this.realConnection = connection;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if (method.getName().equals("close")) {
                System.out.println(Thread.currentThread() + "in invoke");
                myDataSource.releaseConnection((Connection) proxy);
                return null;
            } else {
                return method.invoke(realConnection, args);
            }
        }
    }
}
