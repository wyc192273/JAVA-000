package com.wyc.rpcfx.api;

/**
 * Created by yuchen.wu on 2020-12-13
 */

public class RpcfxException extends RuntimeException {

    private static final long serialVersionUID = 591775911098161330L;

    public RpcfxException() {
    }

    public RpcfxException(String message) {
        super(message);
    }

    public RpcfxException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcfxException(Throwable cause) {
        super(cause);
    }
}
