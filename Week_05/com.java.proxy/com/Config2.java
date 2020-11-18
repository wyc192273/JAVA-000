package com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yuchen.wu on 2020-11-17
 */
@Configuration
public class Config2 {

    @Value("${hello}")
    private String hello;

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}
