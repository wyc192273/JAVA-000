package com.starter.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

/**
 * Created by yuchen.wu on 2020-11-18
 */
@SpringBootApplication
public class SpringBootTestApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringBootTestApplication.class);
        springApplication.addListeners(new ApplicationPidFileWriter("spring-boot-test.pid"));
        springApplication.run(args);


    }
}
