package ru.ftptpf.integration.service;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import ru.ftptpf.database.entity.Role;
import ru.ftptpf.dto.UserCreateEditDto;
import ru.ftptpf.dto.UserReadDto;
import ru.ftptpf.integration.IntegrationTestBase;
import ru.ftptpf.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    private static final Long USER_1 = 1L;
    private static final Integer COMPANY_1 = 1;

    @Autowired
    private UserService userService;

    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        Assertions.assertThat(result).hasSize(5);
    }

    @Test
    void foundById() {
        Optional<UserReadDto> maybeUser = userService.findById(USER_1);
        Assertions.assertThat(maybeUser).isPresent();
        maybeUser.ifPresent(user -> Assertions.assertThat(user.getUsername())
                .isEqualTo("ivan@gmail.com"));
    }

    @Test
    void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "123456",
                LocalDate.now(),
                "Test",
                "Test",
                Role.USER,
                COMPANY_1,
                new MockMultipartFile("image", new byte[0])
        );
        UserReadDto actualResult = userService.create(userDto);
        Assertions.assertThat(actualResult.getUsername()).isEqualTo(userDto.getUsername());
        Assertions.assertThat(actualResult.getBirthDate()).isEqualTo(userDto.getBirthDate());
        Assertions.assertThat(actualResult.getFirstname()).isEqualTo(userDto.getFirstname());
    }

    @Test
    void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "123456",
                LocalDate.now(),
                "Test",
                "Test",
                Role.USER,
                COMPANY_1,
                new MockMultipartFile("image", new byte[0])
        );
        Optional<UserReadDto> actualResult = userService.update(USER_1, userDto);
        Assertions.assertThat(actualResult).isPresent();
        actualResult.ifPresent(user -> {
            Assertions.assertThat(user.getUsername()).isEqualTo(userDto.getUsername());
        });
    }

    @Test
    void delete() {
        boolean result = userService.delete(USER_1);
        Assertions.assertThat(result).isTrue();
    }
}
