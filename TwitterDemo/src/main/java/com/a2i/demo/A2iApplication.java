package com.a2i.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class A2iApplication {


    public static void main(String[] args) {
        SpringApplication.run(A2iApplication.class, args);
    }


}
