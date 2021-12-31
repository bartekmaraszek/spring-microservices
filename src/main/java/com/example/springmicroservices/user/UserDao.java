package com.example.springmicroservices.user;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;

@Component
public class UserDao {
    private static List<User> users = new ArrayList<>();
    private static int userCount;

    static {
        users.add(User.builder().id(1).name("Luke").birthDate(new Date()).build());
        users.add(User.builder().id(2).name("Leia").birthDate(new Date()).build());
        users.add(User.builder().id(3).name("Han").birthDate(new Date()).build());
        users.add(User.builder().id(3).name("Chewie").birthDate(new Date()).build());
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst().orElseThrow(() -> new RuntimeException(
                        format("Could not find user with id = ", id)
                ));
    }
}