package ru.ftptpf.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import ru.ftptpf.database.pool.ConnectionPool;
import ru.ftptpf.service.UserService;

@RequiredArgsConstructor
@ActiveProfiles("test")
@SpringBootTest
public class UserServiceIT {

    @Autowired
    private UserService userService;

    @MockitoSpyBean(name = "pool1")
    private ConnectionPool connectionPool;

    @Test
    void test() {

    }
}
