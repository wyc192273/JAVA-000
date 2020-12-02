package com.wyc.work.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wyc.work.dao.TestDao;

/**
 * Created by yuchen.wu on 2020-11-30
 */
@Service
public class TestService {

    @Resource
    private TestDao testDao;

    public String getNameById(int i) {
        return testDao.getNameById(i);
    }

    public boolean updateNameById(int id, String name) {
        return testDao.updateNameById(id, name);
    }

}
