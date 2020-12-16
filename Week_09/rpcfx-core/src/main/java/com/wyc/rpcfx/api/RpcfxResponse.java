package com.wyc.rpcfx.api;

/**
 * Created by yuchen.wu on 2020-12-13
 */

public class RpcfxResponse {

    public static final byte OK = 20;
    public static final byte ERROR = 51;

    private byte status = OK;

    private Object result;

    private RpcfxException exception;

    public boolean isSuccess() {
        return status == OK;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public RpcfxException getException() {
        return exception;
    }

    public void setException(RpcfxException exception) {
        this.exception = exception;
    }
}
