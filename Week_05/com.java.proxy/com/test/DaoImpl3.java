package com.test;

import org.springframework.stereotype.Repository;

/**
 * Created by yuchen.wu on 2020-11-14
 */
@Repository
public class DaoImpl3 implements Dao3{

    @Override
    public void query() {
        System.out.println("dao3 query");
    }
}
