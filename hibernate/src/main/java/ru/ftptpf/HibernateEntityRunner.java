package ru.ftptpf;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.ftptpf.entity.User;
import ru.ftptpf.util.HibernateUtil;

public class HibernateEntityRunner {


    public static void main(String[] args) {

        User user = User.builder()
                .username("ivan@gmail.com")
                .firstname("Ivan")
                .lastname("Ivanov")
                .build();
//        user после инициализации находится в состоянии Transient

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();
                session1.persist(user);
//        user находится в состоянии Persisted по отношению к session1, но в состоянии Transient по отношению к session2
                session1.getTransaction().commit();
                //        user находится в состоянии Detached по отношению к session1, но в состоянии Transient по отношению к session2

            }
            try (Session session2 = sessionFactory.openSession()) {
                session2.beginTransaction();
                session2.remove(user);
                session2.getTransaction().commit();
                //        user находится в состоянии Removed по отношению к session2
            }
        }

    }
}
