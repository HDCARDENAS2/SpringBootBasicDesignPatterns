package com.learn.desingpatterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.learn.desingpatterns.repository")
@EnableAspectJAutoProxy
public class SpringBootBasicDesignPatterns {
 
    public static void main(String[] args) {
        SpringApplication.run(SpringBootBasicDesignPatterns.class, args);
    }

}
