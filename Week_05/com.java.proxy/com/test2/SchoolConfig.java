package com.test2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yuchen.wu on 2020-11-15
 */
@Configuration
public class SchoolConfig {

    @Bean(name = "school1")
    public School school1(Klass klass1) {
        School school = new School();
        List<Klass> classList = new ArrayList<>();
        classList.add(klass1);
        school.setClasses(classList);
        return school;
    }

    @Bean(name = "school2")
    public School school2(Klass klass2) {
        School school = new School();
        List<Klass> classList = new ArrayList<>();
        classList.add(klass2);
        school.setClasses(classList);
        return school;
    }

    @Bean(name = "klass1")
    public Klass klass1() {
        Klass klass = new Klass();
        return klass;
    }

    @Bean(name = "klass2")
    public Klass klass2() {
        Klass klass = new Klass();
        return klass;
    }

}
