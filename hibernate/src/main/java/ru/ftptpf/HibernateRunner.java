package ru.ftptpf;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.ftptpf.entity.User;

import java.time.LocalDate;

public class HibernateRunner {

    public static void main(String[] args) {
/*        BlockingQueue<Connection> pool = null;
        Connection connection = DriverManager.getConnection("db.url", "db.username", "db.password");*/
        Configuration configuration = new Configuration();
        /*        configuration.configure("hibernate.properties");*/
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            User user = User.builder()
                    .username("ivan@gmail.com")
                    .firstname("Ivan")
                    .lastname("Ivanov")
                    .birthDay(LocalDate.of(1990, 1, 1))
                    .age(35)
                    .build();
            session.merge(user);
        }
    }
}
