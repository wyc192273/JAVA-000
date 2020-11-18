package com.stater.test;

import java.util.ArrayList;
import java.util.List;

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
@EnableConfigurationProperties(Klass.class)
@ConditionalOnClass(Klass.class)
public class KlassConfig {

    @Autowired
    private Klass klass;
    @Autowired
    private Student student;

    @Bean("klass")
    public Klass klass() {
        List<Student> students = new ArrayList<>();
        students.add(student);
        klass.setStudents(students);
        return klass;
    }

}
