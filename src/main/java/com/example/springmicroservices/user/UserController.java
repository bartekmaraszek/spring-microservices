package com.example.springmicroservices.user;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilderDsl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        User user = userDao.findOne(userId);
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(link.withRel("all-users"));

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

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<User> deleteById(@PathVariable("userId") int userId) {
        userDao.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
