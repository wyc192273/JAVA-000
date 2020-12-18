package com.wyc.exchange.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author yuchen.wu
 * @date 2020-12-16
 */
@Data
public class Account implements Serializable {

    private static final long serialVersionUID = -4274258665011951173L;
    private int id;
    private int rmb;
    private int dollar;


}
