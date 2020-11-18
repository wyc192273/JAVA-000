package com.test2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yuchen.wu on 2020-11-15
 */

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("context3.xml");
        Student student = xmlApplicationContext.getBean("student", Student.class);
        System.out.println(student);
        Klass klass = xmlApplicationContext.getBean("class1", Klass.class);
        System.out.println(klass);
        School school = xmlApplicationContext.getBean("school1", School.class);
        System.out.println(school);
    }

}
