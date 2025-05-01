package ru.ftptpf.database.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ru.ftptpf.database.pool.ConnectionPool;

@Scope(BeanDefinition.SCOPE_SINGLETON)
@Repository
public class UserRepository {

    private final ConnectionPool pool;

    public UserRepository(@Qualifier("pool1") ConnectionPool pool) {
        this.pool = pool;
    }
}
