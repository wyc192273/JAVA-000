package com.wyc.rpcfx.api;

/**
 * Created by yuchen.wu on 2020-12-13
 */

public interface RpcfxResolver {

    <T> T resolve(Class<T> serviceClass, String basePackageScan) throws RpcfxException;

}
