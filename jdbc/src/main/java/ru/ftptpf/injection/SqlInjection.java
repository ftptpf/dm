package ru.ftptpf.injection;

import ru.ftptpf.util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Работаем с предварительно созданной таблицей info(id SERIAL PRIMARY KEY, date TEXT NOT NULL)
 * и вставленными ранее в базу значениями 'test2', 'test3'
 */

public class SqlInjection {

    public static void main(String[] args) throws SQLException {
//        String dataText = "'test2'";
        String dataText = "'test2' OR '1'='1'"; // SQL injection
        List<Integer> result = getIdList(dataText);
        System.out.println(result);

    }

    private static List<Integer> getIdList(String dataText) throws SQLException {
        String sql = "SELECT id FROM info WHERE date = %s;".formatted(dataText);
        ArrayList<Integer> result = new ArrayList<>();
        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
//                result.add(resultSet.getInt("id"));
                result.add(resultSet.getObject("id", Integer.class)); // NULL safe
            }
        }
        return result;
    }
}
