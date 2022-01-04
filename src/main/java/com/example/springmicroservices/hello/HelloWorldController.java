package com.example.springmicroservices.hello;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@AllArgsConstructor
public class HelloWorldController {

    // Reads from messages.properties
    private MessageSource messageSource;

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-i18n")
    public String helloWorldI18n(
// We can either accept Locale as a param or read from context
// @RequestHeader(name="Accept-Language", required=false) Locale locale
    ) {
        // We'll accept a non-mandatory header Accept-Language
        // and read the message from messages.properties
        // In IntelliJ, go to properties and set the default encoding
        // of files AND property files to UTF-8 to display non-latin characters
        return messageSource.getMessage(
                "good.morning.message",
                null,
                "Default Message",
                LocaleContextHolder.getLocale()
        );
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
