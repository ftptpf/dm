package ru.ftptpf.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.ftptpf.database.repository.CompanyRepository;
import ru.ftptpf.database.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public UserService(@Qualifier("userRepository") UserRepository userRepository,
                       CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }


}
