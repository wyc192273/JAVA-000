package com.wyc.rpcfx.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wyc.rpcfx.api.RpcfxException;
import com.wyc.rpcfx.api.RpcfxRequest;
import com.wyc.rpcfx.api.RpcfxResolver;
import com.wyc.rpcfx.api.RpcfxResponse;

/**
 * Created by yuchen.wu on 2020-12-13
 */

public class RpcfxInvoker {

    private RpcfxResolver resolver;
    private String basePackageScan;

    public RpcfxInvoker(RpcfxResolver resolver, String basePackageScan){
        this.resolver = resolver;
        this.basePackageScan = basePackageScan;
    }

    public <T> RpcfxResponse invoke(RpcfxRequest<T> request) {
        RpcfxResponse response = new RpcfxResponse();
        Class<T> serviceClass = request.getServiceClass();

        try {
            T service = resolver.resolve(serviceClass, basePackageScan);
            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            Object result = method.invoke(service, request.getParams()); // dubbo, fastjson,
            // 两次json序列化能否合并成一个
            response.setResult(result);
            response.setStatus(RpcfxResponse.OK);
            return response;
        } catch (RpcfxException e) {
            response.setException(e);
            response.setStatus(RpcfxResponse.ERROR);
            return response;
        } catch (Exception e) {
            // 3.Xstream @todo
            response.setException(new RpcfxException(e));
            response.setStatus(RpcfxResponse.ERROR);
            return response;
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }
}
