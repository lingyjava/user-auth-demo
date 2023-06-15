package com.ly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Spring Boot Starter
 *
 * @author Frank Zhang
 */
@SpringBootApplication(scanBasePackages = {"com.ly", "com.alibaba.cola"})
@ServletComponentScan("com.ly.filter")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
