package ru.ftptpf;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateRunner {

    public static void main(String[] args) {
/*        BlockingQueue<Connection> pool = null;
        Connection connection = DriverManager.getConnection("db.url", "db.username", "db.password");*/
        Configuration configuration = new Configuration();
        /*        configuration.configure("hibernate.properties");*/
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            System.out.println("Session started: " + session.isConnected());
        }
    }
}
