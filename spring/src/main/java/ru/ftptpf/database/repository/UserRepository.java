package ru.ftptpf.database.repository;

import ru.ftptpf.database.pool.ConnectionPool;

public class UserRepository {

    private final ConnectionPool pool;

    public UserRepository(ConnectionPool pool) {
        this.pool = pool;
    }
}
