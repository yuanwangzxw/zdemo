package com.zxw.authorizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthorizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizerApplication.class, args);
    }

}
