package com.example.springmicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMicroservicesApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(SpringMicroservicesApplication.class, args);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
