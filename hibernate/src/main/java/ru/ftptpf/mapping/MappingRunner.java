package ru.ftptpf.mapping;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.ftptpf.entity.Birthday;
import ru.ftptpf.entity.Company;
import ru.ftptpf.entity.PersonalInfo;
import ru.ftptpf.entity.User;
import ru.ftptpf.util.HibernateUtil;

import java.time.LocalDate;

@Slf4j
public class MappingRunner {

    public static void main(String[] args) {
        Company company = Company.builder()
                .name("BBT")
                .build();
        User user = User.builder()
                .username("oleg@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Oleg")
                        .lastname("Skripkin")
                        .birthDay(new Birthday(LocalDate.of(2008, 12, 7)))
                        .build())
                .company(company)
                .build();

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.persist(company);
                log.info("We save company: {}", company);
                session.persist(user);
                log.info("We save user: {}", user);
                session.getTransaction().commit();
            }
        }
    }
}
