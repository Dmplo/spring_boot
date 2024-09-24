package ru.gb.hw.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.hw.model.User;
import ru.gb.hw.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @GetMapping
    public String findAll(Model model) {
        List<User> users = service.findAll();

        model.addAttribute("users", users);
        return "users-list.html";
    }

    @GetMapping(value = "/create")
    public String createUserForm() {
        return "user-create.html";
    }

    @PostMapping(value = "/create")
    public String create(User user) {
        service.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String findAll(Model model, @PathVariable("id") Long id) {
        User user = service.findById(id);

        model.addAttribute("user", user);
        return "user-update.html";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute("user") User user) {
        System.out.println(user);
        service.update(user);
        return "redirect:/users";
    }

}
