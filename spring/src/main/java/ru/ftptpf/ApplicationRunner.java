package ru.ftptpf;

import ru.ftptpf.database.pool.ConnectionPool;
import ru.ftptpf.database.repository.CompanyRepository;
import ru.ftptpf.database.repository.UserRepository;
import ru.ftptpf.ioc.Container;
import ru.ftptpf.service.UserService;

public class ApplicationRunner {

    public static void main(String[] args) {
        Container container = new Container();
/*        ConnectionPool connectionPool = new ConnectionPool();
        UserRepository userRepository = new UserRepository(connectionPool);
        CompanyRepository companyRepository = new CompanyRepository(connectionPool);
        UserService userService = new UserService(userRepository, companyRepository);*/

/*        ConnectionPool connectionPool = container.get(ConnectionPool.class);
        UserRepository userRepository = container.get(UserRepository.class);
        CompanyRepository companyRepository = container.get(CompanyRepository.class);*/

        UserService userService = container.get(UserService.class);
    }
}
