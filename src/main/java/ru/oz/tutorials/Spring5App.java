package ru.oz.tutorials;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "ru.oz.tutorials" })
public class Spring5App {


    public static void main(String[] args) {
        SpringApplication.run(Spring5App.class, args);
    }

}
