package com.test3;


import java.sql.SQLException;

/**
 * Created by yuchen.wu on 2020-11-18
 */

public class DataSourceDemo {

    public static void main(String[] args) throws SQLException, InterruptedException {
        MyDataSource dataSource = MyDataSource.getInstance();
        SqlTestUtil sqlTestUtil = SqlTestUtil.getInstance();
        sqlTestUtil.querySchoolTest(dataSource);
        sqlTestUtil.updateSchoolTest(dataSource);
        sqlTestUtil.deleteStudent(dataSource);
        sqlTestUtil.insertStudent(dataSource);
        sqlTestUtil.batchInsert(dataSource);
        sqlTestUtil.batchDelete(dataSource);
        // 终极原因是代理问题。
        sqlTestUtil.batchQuerySchoolTest(dataSource);
    }

}
