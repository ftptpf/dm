package ru.ftptpf;

import ru.ftptpf.util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS info(
                id SERIAL PRIMARY KEY,
                date TEXT NOT NULL);
                """;
        String sqlInsert = """
                INSERT INTO info (date)
                VALUES ('test2'), ('test3');
                """;
        String sqlSelect = """
                SELECT * FROM info;
                """;
        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement()) {
            System.out.println(connection.getTransactionIsolation());
            System.out.println(connection.getSchema());

            boolean executeResult = statement.execute(sql);
            System.out.println(executeResult);

            int executeInsertResult = statement.executeUpdate(sqlInsert);
            System.out.println("Количество вставленных строк: " + executeInsertResult);

            ResultSet resultSelect = statement.executeQuery(sqlSelect);
            while (resultSelect.next()) {
                System.out.println(resultSelect.getInt("id") + " " + resultSelect.getString("date"));
            }

            int executeResultInsert = statement.executeUpdate(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt("id");
                System.out.println("Добавленная запись имеет id: " + generatedId);
            }
        }
    }
}