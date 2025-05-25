package ru.ftptpf.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ftptpf.dto.UserCreateEditDto;
import ru.ftptpf.service.UserService;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String findAll(Model model) {
//        model.addAttribute("users", userService.findAll());
//        model.addAttribute("users", userService.findAll(filter));
        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("users", userService.findById(id));
        return "user/user";
    }

    @PostMapping
    public String create(@ModelAttribute("user") UserCreateEditDto user) {
//        userService.create(user);
        return "redirect:/users" + 25;
    }

//    @PutMapping("{id}")
    @PostMapping("{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute("user") UserCreateEditDto user) {
//        userService.update(id, user);
        return "redirect:/users/{id}";
    }

//    @DeleteMapping("/{id}")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
//        serviceUser.delete(id);
        return "redirect:/users";
    }

}
