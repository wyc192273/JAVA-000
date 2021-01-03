package com.wyc.rpcfx.api;

/**
 * @author yuchen.wu
 * @date 2020-12-21
 */

public abstract class AbstractRegistry implements Registry {

    private RpcfxRegistryConfig registryConfig;

    public AbstractRegistry(RpcfxRegistryConfig registryConfig) {
        this.registryConfig = registryConfig;
    }

}
