package ru.ftptpf.pooll;

import ru.ftptpf.util.ConnectionPoolManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PoolRunner {

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
        try (Connection connection = ConnectionPoolManager.get();
             Statement statement = connection.createStatement()) {

            boolean executeResult = statement.execute(sql);
            System.out.println(executeResult);

            int executeInsertResult = statement.executeUpdate(sqlInsert);
            System.out.println("Количество вставленных строк: " + executeInsertResult);

            ResultSet resultSelect = statement.executeQuery(sqlSelect);
            while (resultSelect.next()) {
                System.out.println(resultSelect.getInt("id") + " " + resultSelect.getString("date"));
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt("id");
                System.out.println("Добавленная запись имеет id: " + generatedId);
            }
        } finally {
            ConnectionPoolManager.closePool();
        }
    }
}
