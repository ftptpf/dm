package ru.ftptpf.integration.database.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import ru.ftptpf.database.entity.Company;
import ru.ftptpf.database.repository.CompanyRepository;
import ru.ftptpf.integration.annotation.IT;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
//@Transactional
@Rollback
//@Commit
class CompanyRepositoryIT {

    private static final Integer APPLE_ID = 6;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void delete() {
        Optional<Company> maybeCompany = companyRepository.findById(APPLE_ID);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertTrue(companyRepository.findById(APPLE_ID).isEmpty());
    }

    @Test
    void findById() {
        transactionTemplate.executeWithoutResult(tx -> {
            Company company = entityManager.find(Company.class, 1);
            assertNotNull(company);
            assertThat(company.getLocales()).hasSize(2);
        });
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