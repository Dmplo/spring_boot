package dev.plotnikov.users.service;

import dev.plotnikov.users.models.Role;
import dev.plotnikov.users.models.User;
import dev.plotnikov.users.repositories.RoleRepository;
import dev.plotnikov.users.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь " + username + " не найден"));
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    public Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Роль " + roleName + " не найдена!"));
    }

    public void addRoleToUser(String username, String roleName) {
        User user = getUserByUsername(username);
        Role role = getRoleByName(roleName);
        user.getRoles().add(role);
    }

}
