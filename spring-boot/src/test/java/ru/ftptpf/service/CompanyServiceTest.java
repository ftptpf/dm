package ru.ftptpf.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import ru.ftptpf.database.entity.Company;
import ru.ftptpf.database.repository.CrudRepository;
import ru.ftptpf.dto.CompanyReadDto;
import ru.ftptpf.listener.entity.EntityEvent;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    public static final Integer COMPANY_ID = 1;

    @Mock
    private CrudRepository<Integer, Company> companyRepository;
    @Mock
    private UserService userService;
    @Mock
    private ApplicationEventPublisher eventPublisher;
    @InjectMocks
    private CompanyService companyService;

    @Test
    void findById() {
        Mockito.doReturn(Optional.of(new Company(COMPANY_ID, null, Collections.emptyMap())))
                .when(companyRepository).findById(COMPANY_ID);

        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);

        Assertions.assertTrue(actualResult.isPresent());

        CompanyReadDto expectedResult = new CompanyReadDto(COMPANY_ID);

        actualResult.ifPresent(actual -> Assertions.assertEquals(expectedResult, actual));
        Mockito.verify(eventPublisher).publishEvent(Mockito.any(EntityEvent.class));
        Mockito.verifyNoMoreInteractions(eventPublisher, userService);
    }
}