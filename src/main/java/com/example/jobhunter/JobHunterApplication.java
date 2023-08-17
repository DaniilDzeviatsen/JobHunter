package com.example.jobhunter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication (exclude = UserDetailsServiceAutoConfiguration.class)
@ConfigurationPropertiesScan
public class JobHunterApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobHunterApplication.class, args);
    }

}
