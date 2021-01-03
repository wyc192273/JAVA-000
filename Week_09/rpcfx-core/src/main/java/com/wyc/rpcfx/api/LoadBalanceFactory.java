package com.wyc.rpcfx.api;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuchen.wu
 * @date 2020-12-26
 */

public class LoadBalanceFactory {

    private static final Map<String, LoadBalance> LOAD_BALANCE_MAP = new HashMap<>();

    static {
        LOAD_BALANCE_MAP.put("random", new RandomBalance());
    }

    public static LoadBalance get(String name) {
        return LOAD_BALANCE_MAP.get(name);
    }

}
