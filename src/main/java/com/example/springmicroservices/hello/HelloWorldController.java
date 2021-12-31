package com.example.springmicroservices.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello from a Bean");
    }

    @GetMapping("/hello/{name}")
    public HelloWorldBean helloName(@PathVariable String name) {
        return new HelloWorldBean("Hello " + name + "!");
    }
}
