package dev.plotnikov.web.service;

import dev.plotnikov.web.client.UsersClientApi;
import dev.plotnikov.web.models.RoleDTO;
import dev.plotnikov.web.models.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersClientApi usersClientApi;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserDTO> getUser(String username) {
        ResponseEntity<Optional<UserDTO>> response = usersClientApi.findUser(username);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new NoSuchElementException("Пользователь с именем " + username + " не найден");
    }


    public void save(UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersClientApi.create(user);
    }

    public void saveRole(RoleDTO role) {
        usersClientApi.createRole(role);
    }

    public void addRoleToUser(String username, String roleName) {
        usersClientApi.addRole(roleName, username);
    }

}
