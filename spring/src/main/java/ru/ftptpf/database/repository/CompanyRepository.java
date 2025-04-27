package ru.ftptpf.database.repository;

import ru.ftptpf.database.pool.ConnectionPool;

public class CompanyRepository {

    private final ConnectionPool pool;

    public CompanyRepository(ConnectionPool pool) {
        this.pool = pool;
    }

    public static CompanyRepository of(ConnectionPool pool) {
        return new CompanyRepository(pool);
    }
}
