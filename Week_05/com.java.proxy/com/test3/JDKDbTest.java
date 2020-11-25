package com.test3;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by yuchen.wu on 2020-11-24
 */

public class JDKDbTest {

    public static void main(String[] args) {
        testDriverManager();
    }

    private static void testDriverManager() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            System.out.println(driver.getClass().getName() + " --- " + driver);
        }
    }

    private static Collection<Object> test() {
        return new ArrayList<>();
    }


}
