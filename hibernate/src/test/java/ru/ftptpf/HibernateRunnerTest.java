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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

class HibernateRunnerTest {

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