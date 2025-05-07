package ru.ftptpf.integration.database.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.ftptpf.database.entity.Company;
import ru.ftptpf.integration.annotation.IT;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IT
@Transactional
@Rollback
//@Commit
class CompanyRepositoryIT {

    @Autowired
    private EntityManager entityManager;

    @Test
    void findById() {
        Company company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(2);
    }

    @Test
    void save() {
        Company company = Company.builder()
                .name("Apple")
                .locales(Map.of("ru", "Apple описание", "en", "Apple description"))
                .build();
        entityManager.persist(company);
        assertNotNull(company.getId());

    }

}