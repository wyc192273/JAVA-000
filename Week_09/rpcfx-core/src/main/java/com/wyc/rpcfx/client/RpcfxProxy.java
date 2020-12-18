package com.wyc.rpcfx.client;

import com.wyc.rpcfx.api.OkHttpClientProtocol;
import com.wyc.rpcfx.api.Protocol;
import com.wyc.rpcfx.api.RpcfxResolver;

/**
 * Created by yuchen.wu on 2020-12-13
 */
public class RpcfxProxy {

    private static Protocol protocol = new OkHttpClientProtocol();

    public static <T> T create(final Class<T> serviceClass, final String url) {
        RpcfxServiceWrapper<T> rpcfxServiceWrapper = protocol.refer(serviceClass, url);
        return rpcfxServiceWrapper.createProxy();
    }

    public static void main(String[] args) {
        RpcfxResolver rpcfxResolver = RpcfxProxy.create(RpcfxResolver.class, "local");

    }
}
