package ru.ftptpf.database.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.ftptpf.database.pool.ConnectionPool;

@Repository
public class UserRepository {

    private final ConnectionPool pool;

    public UserRepository(@Qualifier("pool1") ConnectionPool pool) {
        this.pool = pool;
    }
}
