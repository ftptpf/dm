package ru.ftptpf.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.ftptpf.database.pool.ConnectionPool;
import ru.ftptpf.database.repository.UserRepository;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ru.ftptpf")
public class ApplicationConfiguration {

    @Bean("pool2")
    public ConnectionPool pool() {
        return new ConnectionPool("test-name", 5);
    }

    @Bean("pool3")
    public ConnectionPool pool3(@Value("${db.username}") String username) {
        return new ConnectionPool(username, 25);
    }

    @Bean("userRepository2")
    public UserRepository userRepository(@Qualifier("pool2") ConnectionPool pool) {
        return new UserRepository(pool);
    }

    @Bean("userRepository3")
    public UserRepository userRepository3() {
        return new UserRepository(pool());
    }
}
