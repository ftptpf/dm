package ru.ftptpf;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ftptpf.entity.Birthday;
import ru.ftptpf.entity.PersonalInfo;
import ru.ftptpf.entity.Role;
import ru.ftptpf.entity.User;
import ru.ftptpf.util.HibernateUtil;

import java.time.LocalDate;

public class HibernateRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateRunner.class);

    public static void main(String[] args) {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            LOGGER.info("SessionFactory: {}", sessionFactory);

            session.beginTransaction();

            User user = User.builder()
                    .username("anna@gmail.com")
                    .personalInfo(PersonalInfo.builder()
                            .firstname("Anna")
                            .lastname("Ivanova")
                            .birthDay(new Birthday(LocalDate.of(1990, 11, 20)))
                            .build())
                    .role(Role.ADMIN)
                    .info("""
                            {
                                "name": "Anna",
                                "lastname": "Ivanova"
                            }
                            """)
                    .build();

            LOGGER.info("User: {}", user);

            /*
             * save = persist - добавляет объект в базу данных
             * update = merge - обновляет объект в базе данных
             * saveOrUpdate = persist если entity is transient ИЛИ merge если entity is detached
             * delete = remove - удаляет объект из базы данных
             * get = find - возвращает объект из базы данных
             */
            /*            session.merge(user);*/
            /*            session.remove(user);*/
/*            PersonalInfo key = PersonalInfo.builder()
                    .firstname("Anna")
                    .lastname("Ivanova")
                    .birthDay(new Birthday(LocalDate.of(1990, 11, 20)))
                    .build();
            session.persist(user);*/
            User anna1 = session.find(User.class, 1);
            /*
             * Три варианта удаления нашей сущности из кеша 1-го уровня:
             * 1. session.evict(anna);
             * 2. session.clear();
             * 3. session.close();
             * Кеш 1го уровня привязан к сессии. Как правило, он чистится при закрытии сессии.
             * Намеренно чистить его не нужно.
             * Все изменения сущностей в рамках сессии отразятся в базе данных
             * (в обращениях к базе данных через SQL запросы).
             * Это будет Dirty session.
             * Flush - все изменения в сущностях в кеше 1го уровня в рамках одной сессии сливаются в базу данных.
             * Persistence Context сессии = кеш 1го уровня сессии.
             */
/*            session.evict(anna1);*/
            User anna2 = session.find(User.class, 1);

            System.out.println(anna1.getUsername());
            System.out.println(anna2.getUsername());

            session.getTransaction().commit();
        }
    }
}
