package com.example.dbeaver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching
public class DbeaverApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbeaverApplication.class, args);
    }

}
