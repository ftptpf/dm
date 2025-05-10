package ru.ftptpf.integration.database.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.test.annotation.Rollback;
import ru.ftptpf.database.entity.User;
import ru.ftptpf.database.repository.UserRepository;
import ru.ftptpf.integration.annotation.IT;

import java.util.List;

@IT
@Rollback
class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @Test
    void checkQueries() {
        List<User> users = userRepository.findAllBy("a", "ov");
        Assertions.assertThat(users).hasSize(3);

    }
}