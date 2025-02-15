package ru.t1.java.demo;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LogProperties.class)
public class LogConfigurations {

    @Bean
    public LogAspect loggerAspect(LogProperties logProperties) {
        return new LogAspect(logProperties);
    }

}