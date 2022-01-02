package com.example.springmicroservices.user;

import static java.lang.String.format;

public class UserNotFoundException extends RuntimeException {

    public static String message = "Could not find user with id = %d";
    public UserNotFoundException(int userId) {
        super(format(message, userId));
    }
}
