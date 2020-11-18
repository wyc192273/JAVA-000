package com.stater.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yuchen.wu on 2020-11-18
 */
@Configuration
@EnableConfigurationProperties(Student.class)
@ConditionalOnClass(Student.class)
@ConditionalOnProperty(prefix = "student", name = "isopen", havingValue = "true")
public class StudentConfig {

    @Autowired
    private Student student;

    @Bean("student")
    public Student student() {
        return student;
    }
}
