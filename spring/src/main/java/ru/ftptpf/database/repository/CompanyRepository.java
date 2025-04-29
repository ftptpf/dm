package ru.ftptpf.database.repository;

import ru.ftptpf.bpp.InjectBean;
import ru.ftptpf.database.pool.ConnectionPool;

public class CompanyRepository {

    @InjectBean
    private ConnectionPool pool;

}
