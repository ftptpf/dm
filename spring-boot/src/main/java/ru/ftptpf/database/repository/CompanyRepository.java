package ru.ftptpf.database.repository;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ru.ftptpf.database.entity.Company;
import ru.ftptpf.database.pool.ConnectionPool;

import java.util.List;
import java.util.Optional;

@Slf4j
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Repository

public class CompanyRepository implements CrudRepository<Integer, Company> {

    private final ConnectionPool pool;
    private final List<ConnectionPool> pools;
    private final Integer poolSize;

    public CompanyRepository(@Qualifier("pool1") ConnectionPool pool,
                             List<ConnectionPool> pools,
                             @Value("${db.pool.size}") Integer poolSize) {
        this.pool = pool;
        this.pools = pools;
        this.poolSize = poolSize;
    }

    @PostConstruct
    private void init() {
        log.info("init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        log.info("find by id method ...");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        log.info("delete method ...");
    }
}
