package com.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yuchen.wu on 2020-11-15
 */
@Configuration
public class ServiceConfiguration {

    @Bean(name = "dao4")
    public Dao4 dao4() {
        return new DaoImpl4();
    }

}
