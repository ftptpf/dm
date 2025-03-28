package ru.ftptpf.statement;

import ru.ftptpf.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreparedStatementRunner {

    public static void main(String[] args) throws SQLException {
        String dataText = "test2";
        List<Integer> result = getIdList(dataText);
        System.out.println(result);
    }

    private static List<Integer> getIdList(String dataText) throws SQLException {
        String sql = "SELECT id FROM info WHERE date = ?";
        ArrayList<Integer> result = new ArrayList<>();
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setFetchSize(3);
            preparedStatement.setQueryTimeout(10);
            preparedStatement.setMaxRows(100);

            preparedStatement.setString(1, dataText);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getObject("id", Integer.class)); // NULL safe
            }
        }
        return result;
    }
}
