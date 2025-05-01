package ru.ftptpf;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.ftptpf.config.ApplicationConfiguration;
import ru.ftptpf.database.pool.ConnectionPool;
import ru.ftptpf.database.repository.CrudRepository;

public class ApplicationRunner {

    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
            ConnectionPool connectionPool = context.getBean("pool1", ConnectionPool.class);
            System.out.println(connectionPool);
            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }
    }
}
