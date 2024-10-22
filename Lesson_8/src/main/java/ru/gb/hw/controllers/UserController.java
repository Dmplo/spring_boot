package ru.gb.hw.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.hw.models.User;
import ru.gb.hw.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/")
    public String findAll(Model model) {
        List<User> users = service.getUsers();

        model.addAttribute("users", users);
        return "users-list.html";
    }

    @GetMapping(value = "/create")
    public String createUserForm(Model model) {
        model.addAttribute("roles", List.of("ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"));

        return "user-create.html";
    }

    @PostMapping(value = "/create")
    public String create(User user, @RequestParam(required = false) String role) {
       User created = service.save(user);
        service.addRoleToUser(created.getUsername(), role);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{username}")
    public String findByLogin(Model model, @PathVariable("username") String username) {
        User user = service.getUserByUsername(username);
        model.addAttribute("user", user);
        return "user-update.html";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute("user") User user) {
        service.update(user);
        return "redirect:/";
    }

}
