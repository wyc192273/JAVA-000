package com.starter.test;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import com.stater.test.Klass;
import com.stater.test.School;
import com.stater.test.Student;

/**
 * Created by yuchen.wu on 2020-11-18
 */
@Configuration
public class TestListener implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        School school = applicationContext.getBean("school", School.class);
//        System.out.println(school);
        Student student = applicationContext.getBean("student", Student.class);
        System.out.println(student);
        Klass klass = applicationContext.getBean("klass", Klass.class);
        System.out.println(klass);
        School school = applicationContext.getBean("school", School.class);
        System.out.println(school);
    }
}
