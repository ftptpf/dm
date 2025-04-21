package ru.ftptpf.util;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.ftptpf.entity.*;

@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(Profile.class);
        configuration.addAnnotatedClass(Chat.class);
        configuration.addAnnotatedClass(UserChat.class);
        configuration.addAnnotatedClass(LocaleInfo.class);
        configuration.addAnnotatedClass(Programmer.class);
        configuration.addAnnotatedClass(Manager.class);
        return configuration.buildSessionFactory();
    }
}
