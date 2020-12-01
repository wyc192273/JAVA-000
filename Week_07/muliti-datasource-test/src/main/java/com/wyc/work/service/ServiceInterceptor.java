package com.wyc.work.service;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.wyc.work.datasource.DynamicDataSourceHolder;
import com.wyc.work.datasource.annotation.ReadOnly;

/**
 * Created by yuchen.wu on 2020-12-01
 */
public class ServiceInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object retVal;
        Method method = methodInvocation.getMethod();
        ReadOnly readOnly = method.getAnnotation(ReadOnly.class);
        if (readOnly == null) {
            DynamicDataSourceHolder.markMaster();
        } else {
            DynamicDataSourceHolder.markSlave();
        }
        retVal = methodInvocation.proceed();
        return retVal;
    }
}
