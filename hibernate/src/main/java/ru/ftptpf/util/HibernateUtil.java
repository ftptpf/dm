package ru.ftptpf.util;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.ftptpf.entity.Company;
import ru.ftptpf.entity.User;

@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Company.class);
        return configuration.buildSessionFactory();
    }
}
