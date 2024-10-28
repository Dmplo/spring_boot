package dev.plotnikov.web.client;

import dev.plotnikov.web.models.RoleDTO;
import dev.plotnikov.web.models.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "hello")
public interface UsersClientApi {

    @GetMapping("/find/{name}")
    ResponseEntity<Optional<UserDTO>> findUser(@PathVariable String name);

    @PostMapping(value = "/create")
    ResponseEntity<Void> create(@RequestBody UserDTO user);

    @PostMapping(value = "/role/create")
    ResponseEntity<Void> createRole(@RequestBody RoleDTO role);

    @GetMapping("/{username}/role/{rolename}")
    ResponseEntity<Void> addRole(@PathVariable("rolename") String rolename, @PathVariable("username") String username);
}