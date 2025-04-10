package ru.ftptpf;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.ftptpf.entity.Birthday;
import ru.ftptpf.entity.User;

import java.time.LocalDate;

import static ru.ftptpf.entity.Role.ADMIN;

public class HibernateRunner {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            User user = User.builder()
                    .username("sveta@gmail.com")
                    .firstname("Sveta")
                    .lastname("Svetikova")
                    .birthDay(new Birthday(LocalDate.of(1999, 2, 12)))
                    .role(ADMIN)
                    .info("""
                            {
                                "name": "Sveta",
                                "lastname": "Svetikova"
                            }
                            """)
                    .build();
            session.persist(user);

            session.getTransaction().commit();
        }
    }
}
