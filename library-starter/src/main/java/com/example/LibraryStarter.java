package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/4/2 14:34
 */
@SpringBootApplication(exclude = {ManagementWebSecurityAutoConfiguration.class})
@EnableWebSecurity
public class LibraryStarter {
    public static void main(String[] args) {
        SpringApplication.run(LibraryStarter.class, args);
    }
}
