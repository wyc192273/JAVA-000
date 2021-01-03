package com.wyc.rpcfx.client;

import java.util.List;

import com.wyc.rpcfx.api.Directory;
import com.wyc.rpcfx.api.LoadBalance;
import com.wyc.rpcfx.api.LoadBalanceFactory;
import com.wyc.rpcfx.api.OkHttpClientProtocol;
import com.wyc.rpcfx.api.Protocol;
import com.wyc.rpcfx.api.Registry;
import com.wyc.rpcfx.api.RpcfxReferenceConfig;
import com.wyc.rpcfx.api.RpcfxResolver;
import com.wyc.rpcfx.api.Url;

/**
 * Created by yuchen.wu on 2020-12-13
 */
public class RpcfxProxy {

    public static <T> T create(final RpcfxReferenceConfig<T> rpcfxReferenceConfig) throws Exception {
        Directory<T> directory = rpcfxReferenceConfig.getDirectory();
        List<Url> urls = directory.doList(rpcfxReferenceConfig);
        LoadBalance loadBalance = LoadBalanceFactory.get(rpcfxReferenceConfig.getLoadBalance());
        Url url = loadBalance.select(urls);
        RpcfxServiceWrapper<T> rpcfxServiceWrapper = rpcfxReferenceConfig.getProtocol()
                .refer(rpcfxReferenceConfig.getInterfaceClass(), url);
        return rpcfxServiceWrapper.createProxy();
    }
}
