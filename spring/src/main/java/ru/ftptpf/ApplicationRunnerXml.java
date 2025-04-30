package ru.ftptpf;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.ftptpf.database.pool.ConnectionPool;
import ru.ftptpf.database.repository.CrudRepository;

public class ApplicationRunnerXml {

    public static void main(String[] args) {

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml")) {
            ConnectionPool connectionPool = context.getBean("pool1", ConnectionPool.class);
            System.out.println(connectionPool);
            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }
    }
}
