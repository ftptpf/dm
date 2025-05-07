package ru.ftptpf.integration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import ru.ftptpf.database.pool.ConnectionPool;

@TestConfiguration
public class TestApplicationRunner {

    @MockitoSpyBean("pool1")
    private ConnectionPool pool;
}
