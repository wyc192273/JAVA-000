package com.wyc.rpcfx.api;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuchen.wu
 * @date 2020-12-25
 */

public class ProtocolFactory {

    private static final Map<String, Protocol> PROTOCOL_MAP = new HashMap<>();

    static {
        PROTOCOL_MAP.put("http", new OkHttpClientProtocol());
        PROTOCOL_MAP.put("netty", new NettyClientProtocol());
    }

    public static Protocol getProtocol(String protocol) {
        return PROTOCOL_MAP.get(protocol);
    }
}
