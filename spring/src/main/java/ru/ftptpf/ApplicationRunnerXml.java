package ru.ftptpf;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.ftptpf.database.pool.ConnectionPool;
import ru.ftptpf.database.repository.CompanyRepository;

public class ApplicationRunnerXml {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        ConnectionPool connectionPool = context.getBean("pool1", ConnectionPool.class);
        System.out.println(connectionPool);
        CompanyRepository companyRepository = context.getBean("companyRepository", CompanyRepository.class);
        System.out.println(companyRepository);
    }
}
