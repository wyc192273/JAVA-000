package com.test;

import org.springframework.stereotype.Repository;

/**
 * Created by yuchen.wu on 2020-11-14
 */

public class DaoImpl4 implements Dao4{

    @Override
    public void query() {
        System.out.println("dao4 query");
    }
}
