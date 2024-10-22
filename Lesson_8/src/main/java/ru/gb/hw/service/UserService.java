package ru.gb.hw.service;


import com.fasterxml.jackson.annotation.JsonView;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.hw.aspect.Recover;
import ru.gb.hw.models.Role;
import ru.gb.hw.models.User;
import ru.gb.hw.repositories.RoleRepository;
import ru.gb.hw.repositories.UserRepository;
import ru.gb.hw.util.Views;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    public void addRoleToUser(String username, String roleName) {
        User user = getUserByUsername(username);
        Role role = getRoleByName(roleName);
        user.getRoles().add(role);
    }

    public Role getRoleByName(String roleName) throws UsernameNotFoundException {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new UsernameNotFoundException("Роль " + roleName + " не найдена!"));
    }

    public User getUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь " + username + " не найден"));
    }
    @Recover
    public void update(User user) {
        User userFromDb = getUserByUsername(user.getUsername());
        if (user.getUsername().equals("john")) {
            throw new RuntimeException("Update user " + user.getUsername());
        }
        BeanUtils.copyProperties(user, userFromDb, "id", "password", "roles", "username");
        userRepository.save(userFromDb);
    }
    @Recover
    @JsonView(Views.Full.class)
    public List<User> getUsers() {
        if (ThreadLocalRandom.current().nextInt(1, 5) % 2 == 0) {
            throw new RuntimeException("Get all users");
        }
        return userRepository.findAll();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
