package ru.ftptpf.service;

import org.springframework.stereotype.Service;
import ru.ftptpf.database.entity.Company;
import ru.ftptpf.database.repository.CrudRepository;
import ru.ftptpf.dto.CompanyReadDto;

import java.util.Optional;

@Service
public class CompanyService {

    private final CrudRepository<Integer, Company> companyRepository;
    private final UserService userService;

    public CompanyService(CrudRepository<Integer, Company> companyRepository, UserService userService) {
        this.companyRepository = companyRepository;
        this.userService = userService;
    }

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(entity -> new CompanyReadDto(entity.id()));
    }

}
