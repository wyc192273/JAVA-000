package com.wyc.rpcfx.api;

import java.util.List;

/**
 * @author yuchen.wu
 * @date 2020-12-20
 */

public interface LoadBalance {

    Url select(List<Url> urls) throws Exception;

}
