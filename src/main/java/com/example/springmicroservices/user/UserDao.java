package com.example.springmicroservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static java.lang.String.format;

@Component
public class UserDao {
    private static List<User> users = new ArrayList<>();
    private static int userCount;

    static {
        users.add(User.builder().id(1).name("Luke").birthDate(new Date()).build());
        users.add(User.builder().id(2).name("Leia").birthDate(new Date()).build());
        users.add(User.builder().id(3).name("Han").birthDate(new Date()).build());
        users.add(User.builder().id(4).name("Chewbacca").birthDate(new Date()).build());
        userCount = users.size();
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
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User deleteById(int id) {
        Iterator<User> iter = users.iterator();
        while(iter.hasNext()) {
            User user = iter.next();
            if(user.getId() == id) {
                iter.remove();
                return user;
            }
        }
        throw new UserNotFoundException(id);
    }
}
