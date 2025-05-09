package ru.ftptpf.database.repository;

import org.springframework.data.repository.Repository;
import ru.ftptpf.database.entity.Company;

import java.util.Optional;

public interface CompanyRepository extends Repository<Company, Integer> {

    Optional<Company> findById(Integer id);

    void delete(Company entity);
}
