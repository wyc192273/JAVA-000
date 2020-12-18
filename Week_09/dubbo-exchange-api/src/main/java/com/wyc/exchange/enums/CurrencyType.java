package com.wyc.exchange.enums;

/**
 * 币种类型
 * @author yuchen.wu
 * @date 2020-12-16
 */

public enum CurrencyType {

    RMB(1, "人民币"),
    DOLLAR(2, "美元");

    private int code;
    private String desc;

    CurrencyType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
