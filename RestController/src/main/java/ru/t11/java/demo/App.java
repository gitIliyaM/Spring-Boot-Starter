package ru.t11.java.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import ru.t1.java.demo.LogProperties;

@SpringBootApplication()
@EnableConfigurationProperties(LogProperties.class)
@ComponentScan(basePackages = {"ru.t1.java.demo", "ru.t11.java.demo"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}