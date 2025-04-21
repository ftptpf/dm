package ru.ftptpf;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import ru.ftptpf.entity.*;
import ru.ftptpf.util.HibernateUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

class HibernateRunnerTest {

    @Test
    void checkH2Inheritance() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Company company = Company.builder()
                    .name("Google")
                    .build();
            session.persist(company);

/*            Programmer programmer = Programmer.builder()
                    .username("sergey@gmail.com")
                    .language(Language.JAVA)
                    .company(company)
                    .build();
            session.persist(programmer);

            Manager manager = Manager.builder()
                    .username("peter@gmail.com")
                    .projectName("Project A")
                    .company(company)
                    .build();
            session.persist(manager);
            session.flush();

            session.clear();

            Programmer programmerFromDb = session.find(Programmer.class, 1L);
            Manager managerFromDb = session.find(Manager.class, 2L);
            System.out.println(programmerFromDb);
            System.out.println(managerFromDb);

            session.getTransaction().commit();

 */
        }
    }

    @Test
    void checkH2() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Company company = Company.builder()
                    .name("DDP")
                    .build();
            session.persist(company);

            session.getTransaction().commit();
        }
    }

    @Test
    void checkLocaleInfo() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Company company = session.find(Company.class, 4L);
/*            company.getLocales().add(LocaleInfo.of("ru", "Описание на русском"));
            company.getLocales().add(LocaleInfo.of("en", "Description on English"));*/
            company.getUsers().forEach((k, v) -> System.out.println(v));

            session.getTransaction().commit();
        }
    }

    @Test
    void checkManyToMane() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = session.find(User.class, 2L);
            Chat chat = session.find(Chat.class, 1L);
            UserChat userChat = UserChat.builder()
                    .createdAt(Instant.now())
                    .createdBy(user.getUsername())
                    .build();
            userChat.setUser(user);
            userChat.setChat(chat);

            session.persist(userChat);

            session.getTransaction().commit();
        }
    }

    @Test
    void checkOneToOne() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = User.builder()
                    .username("sergey@gmail.com")
                    .build();
            Profile profile = Profile.builder()
                    .street("Ульянова 14")
                    .language("ru")
                    .build();
            profile.setUser(user);
            session.persist(user);

            session.getTransaction().commit();
        }
    }

    @Test
    void checkOrhanRemoval() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Company company = session.find(Company.class, 1L);
//            company.getUsers().removeIf(user -> user.getId() == 1L);

            session.getTransaction().commit();
        }
    }

    @Test
    void checkLazyInitialization() {
        Company company = null;
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            company = session.find(Company.class, 1L);

            session.getTransaction().commit();
        }
//        Set<User> users = company.getUsers();
        // Будет LazyInitializationException, т.к. сессия уже закрыта
//        System.out.println(users.size());
    }

    @Test
    void addUserToNewCompany() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        Company company = Company.builder()
                .name("Facebook")
                .build();
        User user = User.builder()
                .username("igor@gmail.com")
                .build();
        company.addUser(user);

        session.persist(company);

        session.getTransaction().commit();
    }

    @Test
    void oneToMany() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = session.find(Company.class, 1L);
        System.out.println(company.getName());

        session.getTransaction().commit();

    }

    @Test
    void checkReflectionApi() throws SQLException, IllegalAccessException {
        User user = User.builder()
                .username("ivan@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Иван")
                        .lastname("Иванов")
                        .birthDay(new Birthday(LocalDate.of(1988, 10, 10)))
                        .build())
                .role(Role.USER)
                .build();
        String sql = """
                INSERT INTO
                %s
                (%s)
                VALUES
                (%s)
                """;
        String tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(tableAnnotation -> tableAnnotation.schema() + "." + tableAnnotation.name())
                .orElse(user.getClass().getName());
        Field[] declaredFields = user.getClass().getDeclaredFields();
        String columnNames = Arrays.stream(declaredFields)
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName()))
                .collect(Collectors.joining(", "));
        String columnValues = Arrays.stream(declaredFields)
                .map(field -> "?")
                .collect(Collectors.joining(", "));
        System.out.println(sql.formatted(tableName, columnNames, columnValues));

        Connection connection = null;
        PreparedStatement preparedStatement = connection.prepareStatement(sql.formatted(tableName, columnNames, columnValues));
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            preparedStatement.setObject(1, declaredField.get(user));
        }
    }
}