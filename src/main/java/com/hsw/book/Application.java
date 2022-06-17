package com.hsw.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        int a = 11;
        SpringApplication.run(Application.class, args);
        System.out.println(a);
    }
}
