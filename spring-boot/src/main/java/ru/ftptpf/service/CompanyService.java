package ru.ftptpf.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ftptpf.database.entity.Company;
import ru.ftptpf.database.repository.CrudRepository;
import ru.ftptpf.dto.CompanyReadDto;
import ru.ftptpf.listener.entity.AccessType;
import ru.ftptpf.listener.entity.EntityEvent;

import java.util.Optional;

@Service
public class CompanyService {

    private final CrudRepository<Integer, Company> companyRepository;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    public CompanyService(CrudRepository<Integer, Company> companyRepository,
                          UserService userService,
                          ApplicationEventPublisher eventPublisher) {
        this.companyRepository = companyRepository;
        this.userService = userService;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return new CompanyReadDto(entity.getId());
                });
    }

}
