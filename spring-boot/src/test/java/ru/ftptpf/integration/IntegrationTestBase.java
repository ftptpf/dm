package ru.ftptpf.integration;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.ftptpf.integration.annotation.IT;

@IT
@Sql({
        "classpath:sql/data.sql"
})
@WithMockUser(username = "test@gmail.com", password = "test", authorities = {"ADMIN", "USER"})
public abstract class IntegrationTestBase {

    private static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:17.5-alpine");

    @BeforeAll
    static void runContainer() {
        container.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("#spring.datasource.url", container::getJdbcUrl);
    }
}