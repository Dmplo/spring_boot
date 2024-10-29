package dev.plotnikov.users.controller;

import dev.plotnikov.users.models.Role;
import dev.plotnikov.users.models.User;
import dev.plotnikov.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/find/{name}")
    public ResponseEntity<Optional<User>> get(@PathVariable("name") String name) {
        return ResponseEntity.ok(Optional.of(service.getUserByUsername(name)));
    }

    @GetMapping("/{username}/role/{rolename}")
    ResponseEntity<Void> addRole(@PathVariable("rolename") String rolename, @PathVariable("username") String username) {
        service.addRoleToUser(username, rolename);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> create(@RequestBody User user) {
        service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/role/create")
    ResponseEntity<Void> createRole(@RequestBody Role role) {
        service.saveRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
