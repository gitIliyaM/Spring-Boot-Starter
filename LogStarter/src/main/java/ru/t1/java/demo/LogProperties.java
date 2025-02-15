package ru.t1.java.demo;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "http.log")
public class LogProperties {

    private Boolean enable;
    private String level;

}
