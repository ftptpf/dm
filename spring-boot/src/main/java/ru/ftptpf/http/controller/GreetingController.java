package ru.ftptpf.http.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.ftptpf.dto.UserReadDto;

import java.util.Map;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    @GetMapping("/hello")
    public ModelAndView hello(ModelAndView modelAndView,
                              HttpServletRequest request) {
        modelAndView.setViewName("greeting/hello");
        modelAndView.addObject("user", new UserReadDto(1L, "Ivan"));
        return modelAndView;
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
    public ModelAndView bye(ModelAndView modelAndView,
                            @SessionAttribute("user") UserReadDto user) {
        modelAndView.setViewName("greeting/bye");
        return modelAndView;
    }
}
