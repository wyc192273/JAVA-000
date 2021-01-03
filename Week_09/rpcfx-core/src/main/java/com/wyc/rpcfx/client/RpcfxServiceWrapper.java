package com.wyc.rpcfx.client;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import com.wyc.rpcfx.api.RpcfxException;
import com.wyc.rpcfx.api.RpcfxRequest;
import com.wyc.rpcfx.api.RpcfxResponse;
import com.wyc.rpcfx.api.Url;

/**
 * Created by yuchen.wu on 2020-12-13
 */

public abstract class RpcfxServiceWrapper<T> implements MethodInterceptor {

    private Class<T> targetClass;
    private Url url;

    public RpcfxServiceWrapper(Class<T> targetClass, Url url) {
        this.targetClass = targetClass;
        this.url = url;
    }

    public T createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        RpcfxRequest request = new RpcfxRequest(targetClass, method.getName(), objects);
        RpcfxResponse response = invoke(request, url);
        rpcfxExceptionHandle(response);
        return response.getResult();
    }

    protected abstract RpcfxResponse invoke(RpcfxRequest req, Url url) throws RpcfxException;

    private void rpcfxExceptionHandle(RpcfxResponse response) {
        if (response.isSuccess()) {
            return;
        }
        RpcfxException rpcfxException = response.getException();
        if (rpcfxException == null) {
            throw new RpcfxException("unknown exception occur");
        }
        throw rpcfxException;
    }

}
