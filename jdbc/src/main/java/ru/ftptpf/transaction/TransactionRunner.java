package ru.ftptpf.transaction;

import ru.ftptpf.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionRunner {

    public static void main(String[] args) throws SQLException {
        int id = 1;
        String sql = "DELETE FROM info WHERE id = ?;";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.open();
            preparedStatement = connection.prepareStatement(sql);

            connection.setAutoCommit(false);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;

        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
}
