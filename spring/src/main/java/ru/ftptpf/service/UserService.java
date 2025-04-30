package ru.ftptpf.service;

import org.springframework.stereotype.Service;
import ru.ftptpf.database.entity.Company;
import ru.ftptpf.database.repository.CrudRepository;
import ru.ftptpf.database.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;

    public UserService(UserRepository userRepository,
                       CrudRepository<Integer, Company> companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }


}
