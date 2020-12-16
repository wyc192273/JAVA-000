package com.wyc.rpcfx.order.api.bean;

import java.io.Serializable;

/**
 * Created by yuchen.wu on 2020-12-14
 */

public class Order implements Serializable {

    private static final long serialVersionUID = -1241580516795442273L;
    private int id;

    private String name;

    private double amount;

    public Order() {
    }

    public Order(int id, String name, double amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
