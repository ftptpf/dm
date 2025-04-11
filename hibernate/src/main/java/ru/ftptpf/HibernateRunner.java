package ru.ftptpf;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.ftptpf.entity.Birthday;
import ru.ftptpf.entity.Role;
import ru.ftptpf.entity.User;
import ru.ftptpf.util.HibernateUtil;

import java.time.LocalDate;

public class HibernateRunner {

    public static void main(String[] args) {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            User user = User.builder()
                    .username("sveta@gmail.com")
                    .firstname("Sveta")
                    .lastname("Svetikova")
                    .birthDay(new Birthday(LocalDate.of(1999, 2, 12)))
                    .role(Role.ADMIN)
                    .info("""
                            {
                                "name": "Sveta",
                                "lastname": "Svetikova"
                            }
                            """)
                    .build();
            /*
             * save = persist - добавляет объект в базу данных
             * update = merge - обновляет объект в базе данных
             * saveOrUpdate = persist если entity is transient ИЛИ merge если entity is detached
             * delete = remove - удаляет объект из базы данных
             * get = find - возвращает объект из базы данных
             */
            /*            session.merge(user);*/
            /*            session.remove(user);*/
            /*            session.persist(user);*/
            User sveta1 = session.find(User.class, "sveta@gmail.com");
            /*
             * Три варианта удаления нашей сущности из кеша 1-го уровня:
             * 1. session.evict(sveta1);
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
            session.evict(sveta1);
            User sveta2 = session.find(User.class, "sveta@gmail.com");

            System.out.println(sveta1.getUsername());
            System.out.println(sveta2.getUsername());

            session.getTransaction().commit();
        }
    }
}
