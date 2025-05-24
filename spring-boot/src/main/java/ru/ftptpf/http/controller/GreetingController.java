package ru.ftptpf.http.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.ftptpf.database.entity.Role;
import ru.ftptpf.dto.UserReadDto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    @ModelAttribute
    public List<Role> roles() {
        return Arrays.asList(Role.values());
    }

    @GetMapping("/hello")
    public String hello(Model model,
                        HttpServletRequest request,
                        @ModelAttribute("userReadDto") UserReadDto userReadDto) {
        model.addAttribute("user", new UserReadDto(1L, "Ivan"));
        return "greeting/hello";
    }

    @GetMapping("/hello2/{id}")
    public ModelAndView hello2(ModelAndView modelAndView,
                               HttpServletRequest request,
                               @RequestParam("age") Integer age,
                               @RequestHeader("User-Agent") String userAgent,
                               @PathVariable("id") Integer id) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Cookie[] cookies = request.getCookies();
        modelAndView.setViewName("greeting/hello");
        return modelAndView;
    }

    @GetMapping("/bye")
    public String bye(@SessionAttribute("user") UserReadDto user) {
        return "greeting/bye";
    }
}
