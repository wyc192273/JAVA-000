package com.wyc.rpcfx.api;

import com.wyc.rpcfx.client.RpcfxServiceWrapper;

/**
 * @// TODO: 2020-12-16  
 * Created by yuchen.wu on 2020-12-14
 */

public class NettyClientProtocol implements Protocol{

    @Override
    public <T> RpcfxServiceWrapper<T> refer(Class<T> target, String url) {
        return new RpcfxServiceWrapper<T>(target, url) {
            @Override
            protected RpcfxResponse invoke(RpcfxRequest req, String url) throws RpcfxException {
//                post(req, url);
                return null;
            }
        };
    }
}
