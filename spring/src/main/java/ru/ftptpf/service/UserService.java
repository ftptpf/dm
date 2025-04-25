package ru.ftptpf.service;

import ru.ftptpf.database.repository.CompanyRepository;
import ru.ftptpf.database.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public UserService(UserRepository userRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }


}
