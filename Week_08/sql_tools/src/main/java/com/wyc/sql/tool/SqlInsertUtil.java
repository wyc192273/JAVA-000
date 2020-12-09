package com.wyc.sql.tool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.UUID;
import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.RandomStringUtils;

import com.wyc.sql.tool.datasource.DataSourceUtil;

/**
 * Created by yuchen.wu on 2020-12-06
 */
@Slf4j
public class SqlInsertUtil {

    public static void main(String[] args) {
        /*tableGenerate("user_address", 8, "(\n"
                + "  `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',\n"
                + "  `user_id`    bigint unsigned NOT NULL DEFAULT 0 COMMENT '用户id',\n"
                + "  `country`    varchar(50)     NOT NULL DEFAULT 0 COMMENT '国家',\n"
                + "  `province`   varchar(50)     NOT NULL DEFAULT 0 COMMENT '省份/州',\n"
                + "  `city`       varchar(50)     NOT NULL DEFAULT 0 COMMENT '地区',\n"
                + "  `detail`     varchar(255)    NOT NULL DEFAULT 0 COMMENT '详细地址',\n"
                + "  `created_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n"
                + "  `updated_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',\n"
                + "  PRIMARY KEY (`id`)\n"
                + ") ENGINE = InnoDB\n"
                + "  DEFAULT CHARSET = utf8mb4 COMMENT ='用户地址表'");*/
        for (int i = 0; i < 10; i++) {
            tableInsertOrder();
        }
    }

    public static void tableGenerate(String tableName, int count, String createSql) {
        String sql = "CREATE TABLE `%s_%s` %s";
        for (int i = 0; i < count; i++) {
            System.out.println(String.format(sql, tableName, i, createSql));
        }
    }

    private static DataSource dataSource;

    static {
        dataSource = DataSourceUtil.createDataSource("127.0.0.1", 3339, "root", "root", "test");

    }

    public static void tableInsertUser() {
        int nameLength = 5;
        int ageRange = 100;
        Random random = new Random();
        String batchInsertSql = "insert into user (`name`, `age`) values (?,?)";
        System.out.println(batchInsertSql);
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(batchInsertSql);
            for (int i = 0; i < 100000; i++) {
                statement.setString(1, createRandomStr3(random.nextInt(nameLength) + 1));
                statement.setInt(2, (random.nextInt(ageRange) + 1));
                statement.addBatch();
            }
            statement.executeBatch();
            statement.close();
        } catch (SQLException e) {
            log.error("error", e);
        }

    }

    public static void tableInsertOrder() {
        int userLength = 1000000;
        int commodityLength = 3;
        int countLength = 100;
        int price = 1000;
        Random random = new Random();
        String batchInsertSql = "insert into `order` (`order_no`, `price`, `memo`, `user_id`, commodity_snapshot) values (?,?,?,?,'{}')";
        String batchInsertSql2 = "insert into `order_related` (`order_no`, `price`, `count`, `commodity_id`) values (?,?,?,?)";
        System.out.println(batchInsertSql);
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(batchInsertSql);
            PreparedStatement statement1 = connection.prepareStatement(batchInsertSql2);
            for (int i = 0; i < 100000; i++) {
                String orderNo = UUID.randomUUID().toString().replaceAll("-","");;
                int price2 = random.nextInt(price) + 1;
                int userId = random.nextInt(userLength) + 1;
                statement.setString(1,  orderNo);
                statement.setInt(2, price2);
                statement.setString(3, createRandomStr3(10));
                statement.setInt(4, userId);
                statement.addBatch();

                statement1.setString(1, orderNo);
                statement1.setInt(2, price2);
                statement1.setInt(3, random.nextInt(countLength) + 1);
                statement1.setInt(4, random.nextInt(commodityLength) + 1);
                statement1.addBatch();
            }
            statement.executeBatch();
            statement1.executeBatch();
            statement.close();
            statement1.close();
        } catch (SQLException e) {
            log.error("error", e);
        }
    }

    public static void tableInsertUser2() {
        int nameLength = 5;
        int ageRange = 100;
        Random random = new Random();
        String batchInsertSql = "insert into user (`name`, `age`) values ('test1',15)";
        for (int index = 0; index < 10; index++) {
            batchInsertSql += ",('" + createRandomStr3(random.nextInt(nameLength)+1) + "'," + (random.nextInt(ageRange) + 1) + ")";
        }
        batchInsertSql += ";";
        System.out.println(batchInsertSql);
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into user (`name`, `age`) values ('test1',15);");
            statement.close();
        } catch (SQLException e) {
            log.error("error", e);
        }

    }

    public static void selectUser2() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }
            statement.close();
        } catch (SQLException e) {
            log.error("error", e);
        }

    }

    private static String createStr(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(length);
            stringBuffer.append(str.charAt(number));
        }
        return stringBuffer.toString();
    }

    public static String createRandomStr3(int length){
        return RandomStringUtils.randomAlphanumeric(length);
    }


}
