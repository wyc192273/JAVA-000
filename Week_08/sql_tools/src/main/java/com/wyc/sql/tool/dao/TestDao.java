package com.wyc.sql.tool.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import com.wyc.sql.tool.datasource.DataSourceUtil;

/**
 * Created by yuchen.wu on 2020-12-06
 */

public class TestDao {

    private static DataSource dataSource;

    static {
        dataSource = DataSourceUtil.createDataSource("127.0.0.1", 3339, "root", "root", "order_test");
    }

    public static void insertOrder(String orderNo, int userId, int price, String memo) {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            String sql = String.format(
                    "insert into `%s` (`order_no`, price, memo, user_id, commodity_snapshot) value ('%s',%s,'%s',%s,'{}');",
                    getOrderTableName(orderNo), orderNo, price, memo, userId);
            System.out.println(sql);
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateOrder(String orderNo,int price) {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            String sql = String.format("update `%s` set price = %s where order_no = '%s'", getOrderTableName(orderNo), price,
                    orderNo);
            System.out.println(sql);
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteOrder(String orderNo) {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            String sql = String.format("delete from `%s` where order_no = '%s'", getOrderTableName(orderNo),
                    orderNo);
            System.out.println(sql);
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void queryOrder(String orderNo) {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            String sql = String.format(
                    "select * from `%s` where order_no = '%s'",
                    getOrderTableName(orderNo), orderNo);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        insertOrder("asdasd12313asd23", 2, 10, "sadad");
        queryOrder("asdasd12313asd23");
        updateOrder("asdasd12313asd23", 100);
        queryOrder("asdasd12313asd23");
        deleteOrder("asdasd12313asd23");
    }

    private static String getOrderTableName(String orderNo) {
        return "order_" + Math.abs(orderNo.hashCode()) % 8;
    }


}
