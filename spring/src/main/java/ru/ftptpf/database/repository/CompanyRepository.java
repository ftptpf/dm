package ru.ftptpf.database.repository;

import jakarta.annotation.PostConstruct;
import ru.ftptpf.bpp.Auditing;
import ru.ftptpf.bpp.InjectBean;
import ru.ftptpf.bpp.Transaction;
import ru.ftptpf.database.entity.Company;
import ru.ftptpf.database.pool.ConnectionPool;

import java.util.Optional;

@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {

    @InjectBean
    private ConnectionPool pool;

    @PostConstruct
    private void init() {
        System.out.println("init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("find by id method ...");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("delete method ...");
    }
}
