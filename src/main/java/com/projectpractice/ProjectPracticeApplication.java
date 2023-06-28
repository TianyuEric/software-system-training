package com.projectpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ProjectPracticeApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProjectPracticeApplication.class, args);
    }

}
