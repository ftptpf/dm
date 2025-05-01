package ru.ftptpf;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.ftptpf.config.ApplicationConfiguration;
import ru.ftptpf.database.pool.ConnectionPool;
import ru.ftptpf.service.CompanyService;

public class ApplicationRunner {

    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
            ConnectionPool connectionPool = context.getBean("pool1", ConnectionPool.class);
            System.out.println(connectionPool);
            var companyService = context.getBean("companyService", CompanyService.class);
            System.out.println(companyService.findById(1));
        }
    }
}
