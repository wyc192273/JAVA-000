package com.wyc.rpcfx.api;

import java.util.List;

/**
 * @author yuchen.wu
 * @date 2020-12-20
 */

public interface Directory<T> {

    List<Url> doList(RpcfxReferenceConfig<T> referenceConfig) throws Exception;

}
