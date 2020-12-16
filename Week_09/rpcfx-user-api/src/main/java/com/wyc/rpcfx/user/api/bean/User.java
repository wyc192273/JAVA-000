package com.wyc.rpcfx.user.api.bean;

import java.io.Serializable;

/**
 * Created by yuchen.wu on 2020-12-14
 */

public class User implements Serializable {

    private static final long serialVersionUID = -8517486141929513213L;
    private int id;
    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
