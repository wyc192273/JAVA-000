package com.wyc.rpcfx.api;

import com.wyc.rpcfx.client.RpcfxServiceWrapper;

/**
 * Created by yuchen.wu on 2020-12-14
 */

public interface Protocol {

    <T> RpcfxServiceWrapper<T> refer(final Class<T> target, final Url url);


}
