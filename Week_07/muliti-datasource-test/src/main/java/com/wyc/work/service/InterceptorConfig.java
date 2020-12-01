package com.wyc.work.service;

import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yuchen.wu on 2020-12-01
 */
@Configuration
public class InterceptorConfig {
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor3() {
        ServiceInterceptor interceptor = new ServiceInterceptor();

        // AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(HfiTrace.class, true);
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPatterns("com.wyc.work.dao.*");

        // 配置增强类advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }
}
