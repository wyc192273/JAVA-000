package com.wyc.rpcfx.api;

import java.util.List;

/**
 * @author yuchen.wu
 * @date 2020-12-26
 */

public class RegistryDirectory<T> implements Directory<T> {

    private Registry registry;

    public RegistryDirectory(Registry registry) {
        this.registry = registry;
    }

    @Override
    public List<Url> doList(RpcfxReferenceConfig<T> referenceConfig) throws Exception {
        return registry.getProviderUrls(referenceConfig);
    }

}
