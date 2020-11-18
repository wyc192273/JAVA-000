package com.stater.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yuchen.wu on 2020-11-18
 */
@Configuration
@EnableConfigurationProperties(School.class)
@ConditionalOnProperty(prefix = "school", name = "isopen", havingValue = "true")
public class SchoolConfig {

    @Autowired
    private School school;
    @Autowired
    private Klass klass;

    @Bean("school")
    public School school() {
        List<Klass> klasses = new ArrayList<>();
        klasses.add(klass);
        school.setClasses(klasses);
        return school;
    }

}
