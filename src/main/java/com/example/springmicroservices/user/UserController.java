package com.example.springmicroservices.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private UserDao userDao;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDao.findAll();
    }

    @GetMapping("/user/{userId}")
    public User retrieveUser(@PathVariable("userId") int userId) {
        return userDao.findOne(userId);
    }

    @PostMapping("user")
    public User createUser(@RequestBody User user) {
        return userDao.save(user);
    }
}
