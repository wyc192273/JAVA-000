package com.wyc.work.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.wyc.work.datasource.DatasourceFactory;

/**
 * Created by yuchen.wu on 2020-12-01
 */
@Slf4j
@Repository
public class TestDao {

    private static DataSource dataSource;

    static {
        try {
            dataSource = DatasourceFactory.newInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNameById(int i) {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select `name` from test_t where id = " + i);
            String result = null;
            while (resultSet.next()) {
                result = resultSet.getString(1);
            }
            statement.close();
            return result;
        } catch (SQLException e) {
            log.error("get connection error", e);
            return null;
        }
    }

    public boolean updateNameById(int id, String name) {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("update test_t set `name` = '" + name + "' where id = " + id);
            statement.close();
            return result != 0;
        } catch (SQLException e) {
            log.error("get connection error", e);
            return false;
        }
    }

}
