package ru.ftptpf.batch;

import ru.ftptpf.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Пакетные запросы в базу данных
 */
public class BatchRunner {

    public static void main(String[] args) throws SQLException {
        String sql1 = "DELETE FROM info WHERE id = 4;";
        String sql2 = "DELETE FROM info WHERE id = sdsfsdf;";
        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionManager.open();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.addBatch(sql1);
            statement.addBatch(sql2);
            statement.executeBatch();
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
            if (statement != null) {
                statement.close();
            }
        }
    }
}
