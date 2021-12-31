package com.example.springmicroservices.hello;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class HelloWorldBean {
    private final String message;
}
