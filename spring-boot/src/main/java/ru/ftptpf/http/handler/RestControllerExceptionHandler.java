package ru.ftptpf.http.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice(basePackages = "ru.ftptpf.http.rest")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {


}
