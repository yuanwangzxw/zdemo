package com.zxw.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//@ServletComponentScan
//@EnableCasClient
public class AuthenticationApplication {


    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }
}
