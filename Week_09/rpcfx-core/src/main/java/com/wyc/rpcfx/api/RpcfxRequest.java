package com.wyc.rpcfx.api;

/**
 * rpc框架 请求实体类
 * Created by yuchen.wu on 2020-12-13
 */

public class RpcfxRequest<T> {

    private Class<T> serviceClass;

    private String method;

    private Object[] params;

    private RpcfxRequest() {

    }

    public RpcfxRequest(Class<T> serviceClass, String method, Object[] params) {
        this.serviceClass = serviceClass;
        this.method = method;
        this.params = params;
    }

    public Class<T> getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(Class<T> serviceClass) {
        this.serviceClass = serviceClass;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
