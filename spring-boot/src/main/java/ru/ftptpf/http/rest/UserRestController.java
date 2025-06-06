package ru.ftptpf.http.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.ftptpf.dto.PageResponse;
import ru.ftptpf.dto.UserReadDto;
import ru.ftptpf.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v2/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

/*    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserReadDto> findAll() {
        Page<UserReadDto> page = userService.findAll(filter, pageable);
        return PageResponse.of(page);
    }*/

    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
