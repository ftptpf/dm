package ru.ftptpf.integration.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ftptpf.dto.CompanyReadDto;
import ru.ftptpf.service.CompanyService;

import java.util.Optional;

@SpringBootTest
/*@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationRunner.class,
        initializers = ConfigDataApplicationContextInitializer.class)*/
public class CompanyServiceIT {

    public static final Integer COMPANY_ID = 1;

    @Autowired
    private CompanyService companyService;

    @Test
    void findById() {
        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);
        Assertions.assertTrue(actualResult.isPresent());
        CompanyReadDto expectedResult = new CompanyReadDto(COMPANY_ID);

        actualResult.ifPresent(actual -> Assertions.assertEquals(expectedResult, actual));
    }
}
