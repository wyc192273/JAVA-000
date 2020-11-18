package com.test;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by yuchen.wu on 2020-11-14
 */
@org.springframework.stereotype.Service
public class Service {

    private Dao1 dao1;
    private Dao2 dao2;
    @Autowired
    private Dao3 dao3;
    @Resource
    private Dao4 dao4;

    public Service() {
    }

    public Service(Dao1 dao1, Dao2 dao2) {
        this.dao1 = dao1;
        this.dao2 = dao2;
    }

    public void query() {
        dao1.query();
        dao2.query();
    }

    public void query2() {
        dao3.query();
    }

    public void query4() {
        dao4.query();
    }

    public void setDao1(Dao1 dao1) {
        this.dao1 = dao1;
    }

    public void setDao2(Dao2 dao2) {
        this.dao2 = dao2;
    }
}
