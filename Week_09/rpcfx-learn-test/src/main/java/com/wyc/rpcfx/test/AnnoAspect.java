package com.wyc.rpcfx.test;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by yuchen.wu on 2020-12-13
 */
@Aspect
public class AnnoAspect {

    @Pointcut("execution(* com.wyc.rpcfx.test.App.say(..))")
    public void joinPoint() {
    }

    @Before("joinPoint()")
    public void before() {
        System.out.println("AnnoAspect before say.");
    }

    @After("joinPoint()")
    public void after() {
        System.out.println("AnnoAspect after say.");
    }

}
