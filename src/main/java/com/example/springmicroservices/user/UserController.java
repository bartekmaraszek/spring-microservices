package com.example.springmicroservices.user;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private UserDao userDao;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        // TODO: eTag and CacheControl
        return userDao.findAll();
    }

    @GetMapping("/user/{userId}")
    public User retrieveUser(@PathVariable("userId") int userId) {
        // TODO: eTag and CacheControl
        return userDao.findOne(userId);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userDao.save(user);
        // This will build the URI and put it in 'Location' header:
        // Location: http://localhost:8080/user/6
        URI resourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        // This will return 201 Created status
        // with the user in the response body
        return ResponseEntity
                .created(resourceLocation)
                .body(savedUser);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<User> deleteById(@PathVariable("userId") int userId) {
        userDao.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
