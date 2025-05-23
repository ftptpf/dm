package ru.ftptpf;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import ru.ftptpf.entity.Company;
import ru.ftptpf.entity.User;
import ru.ftptpf.util.HibernateUtil;

import java.util.List;

public class HqlTest {

    @Test
    void checkHql() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            List<User> list = session.createQuery(
                    "select u from User u where u.personalInfo.firstname = :firstname", User.class)
                    .setParameter("firstname", "Сергей")
                    .setFetchSize(50)
                    .list();
            System.out.println(list.size());

            int countRows = session.createQuery("update User u set u.role = 'USER'", User.class)
                    .executeUpdate();
            System.out.println(countRows);

            session.createNativeQuery("select u.* from User u", User.class);

            session.getTransaction().commit();
        }
    }
}
