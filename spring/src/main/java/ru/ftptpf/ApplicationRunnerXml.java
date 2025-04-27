package ru.ftptpf;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.ftptpf.database.pool.ConnectionPool;

public class ApplicationRunnerXml {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        System.out.println(context.getBean("pool1", ConnectionPool.class));
    }
}
