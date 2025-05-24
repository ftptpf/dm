package ru.ftptpf.http.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/api/v1")
public class GreetingController {

    @GetMapping("/hello/{id}")
    public ModelAndView hello(ModelAndView modelAndView,
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
    public ModelAndView bye(ModelAndView modelAndView) {
        modelAndView.setViewName("greeting/bye");
        return modelAndView;
    }
}
