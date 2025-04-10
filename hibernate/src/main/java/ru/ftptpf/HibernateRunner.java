package ru.ftptpf;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.ftptpf.entity.Birthday;
import ru.ftptpf.entity.Role;
import ru.ftptpf.entity.User;

import java.time.LocalDate;

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
            User sveta = session.find(User.class, "sveta@gmail.com");
            System.out.println(sveta.getUsername());

            session.getTransaction().commit();
        }
    }
}
