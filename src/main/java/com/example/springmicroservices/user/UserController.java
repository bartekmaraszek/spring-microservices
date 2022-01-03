package com.example.springmicroservices.user;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.Affordance;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilderDsl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@AllArgsConstructor
public class UserController {

    private UserDao userDao;

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> retrieveAllUsers() {
        // TODO: eTag and CacheControl
        List<EntityModel<User>> users = userDao.findAll().stream()
                .map(user -> EntityModel.of(
                        user,
                        linkTo(methodOn(this.getClass()).retrieveUser(user.getId())).withSelfRel(),
                        linkTo(methodOn(this.getClass()).retrieveAllUsers()).withRel("users")
                )).collect(Collectors.toList());
        return CollectionModel.of(users, linkTo(methodOn(this.getClass()).retrieveAllUsers()).withSelfRel());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<EntityModel<User>> retrieveUser(@PathVariable("userId") int userId) {
        // TODO: eTag and CacheControl

        // based on: https://www.youtube.com/watch?v=nVEmfmdDUXU 
        Link selfLink = linkTo(methodOn(this.getClass()).retrieveUser(userId)).withSelfRel();
        Affordance update = afford(methodOn(this.getClass()).updateUser(null, userId));
        Link aggregateRoot = linkTo(methodOn(this.getClass()).retrieveAllUsers()).withRel("users");

        User user = userDao.findOne(userId);
        EntityModel<User> model = EntityModel.of(
                user, selfLink.andAffordance(update), aggregateRoot);

        return ResponseEntity
                .status(200)
                .body(model);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
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

    @PatchMapping("/user")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, int userId) {
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<User> deleteById(@PathVariable("userId") int userId) {
        userDao.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
