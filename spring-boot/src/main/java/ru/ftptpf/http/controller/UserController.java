package ru.ftptpf.http.controller;

import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.ftptpf.database.entity.Role;
import ru.ftptpf.dto.UserCreateEditDto;
import ru.ftptpf.service.CompanyService;
import ru.ftptpf.service.UserService;
import ru.ftptpf.validation.group.CreateAction;
import ru.ftptpf.validation.group.UpdateAction;

@Slf4j
@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CompanyService companyService;

    @GetMapping("/users")
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/users";
    }

    @GetMapping("/users/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return userService.findById(id)
                .map(user -> {
                    model.addAttribute("user", user);
                    model.addAttribute("roles", Role.values());
                    model.addAttribute("companies", companyService.findAll());
                    return "user/user";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/users/registration")
    public String registration(Model model, @ModelAttribute("user") UserCreateEditDto user) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("companies", companyService.findAll());
        return "/user/registration";
    }

    @PostMapping("/users")
//    @ResponseStatus(HttpStatus.CREATED)
    public String create(@ModelAttribute("user") @Validated({Default.class, CreateAction.class}) UserCreateEditDto user,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/api/v1/users/registration";
        }
        return "redirect:/api/v1/users/" + userService.create(user).getId();
    }

    //    @PutMapping("{id}")
    @PostMapping("/users/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute @Validated({Default.class, UpdateAction.class}) UserCreateEditDto user) {
        return userService.update(id, user)
                .map(it -> "redirect:/api/v1/users/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //    @DeleteMapping("/{id}")
    @PostMapping("/users/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (userService.delete(id)) {
            return "redirect:/api/v1/users";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
