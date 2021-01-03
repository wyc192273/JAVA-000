package com.wyc.rpcfx.api;

import java.util.List;

/**
 * @author yuchen.wu
 * @date 2020-12-20
 */

public interface Registry {

    void doRegistry(RpcfxServiceConfig serviceConfig) throws Exception;

    void doSubscribe(RpcfxReferenceConfig rpcfxReferenceConfig) throws Exception;

    List<Url> getProviderUrls(RpcfxReferenceConfig rpcfxReferenceConfig) throws Exception;

}
