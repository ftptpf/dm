package ru.ftptpf.blob;

import ru.ftptpf.util.ConnectionManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BlobRunner {

    public static void main(String[] args) throws SQLException, IOException {
        // blob - (bytea in Postgresql) binary large object (photos, video, etc.)
        // clob - (TEXT in Postgresql) character large object (text)
        /*        saveImage();*/
        getImage();
    }

    private static void saveImage() throws SQLException, IOException {
/*        String sqlCreateTable = """
                CREATE TABLE IF NOT EXISTS aircraft
                (
                    id SERIAL PRIMARY KEY,
                    model text NOT NULL,
                    image bytea
                );
                """;
        String sqlInsert = """
                INSERT INTO aircraft(model, image) VALUES ('boeing', null);
                INSERT INTO aircraft(model, image) VALUES ('jat', null);
                INSERT INTO aircraft(model, image) VALUES ('airbus', null);
                """;*/
        String sqlInsertImage = "UPDATE aircraft SET image=? WHERE id=1;";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertImage)) {
            preparedStatement.setBytes(1, Files.readAllBytes(
                    Path.of("jdbc", "src", "main", "resources", "boeing777.jpg")));
            preparedStatement.executeUpdate();
        }
    }

    private static void getImage() throws SQLException, IOException {
        String sqlGetImage = "SELECT image FROM aircraft WHERE id=?;";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlGetImage)) {
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                byte[] image = resultSet.getBytes("image");
                Files.write(Path.of("jdbc", "src", "main", "resources", "boeing777-copy.jpg"), image, StandardOpenOption.CREATE);
            }
        }
    }

/*    private static void saveImage() throws SQLException, IOException {
        String sqlCreateTable = """
                CREATE TABLE IF NOT EXISTS aircraft
                (
                    id SERIAL PRIMARY KEY,
                    model text NOT NULL,
                    image bytea
                );
                """;
        String sqlInsert = """
                INSERT INTO aircraft(model, image) VALUES ('boeing', null);
                INSERT INTO aircraft(model, image) VALUES ('jat', null);
                INSERT INTO aircraft(model, image) VALUES ('airbus', null);
                """;
        String sqlInsertImage = "UPDATE aircraft SET image=? WHERE id=1;";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertImage)) {
            connection.setAutoCommit(false);
            Blob blob = connection.createBlob();
            blob.setBytes(1, Files.readAllBytes(Path.of("src", "main", "resources", "boeing777.jpg")));

            preparedStatement.executeUpdate(sqlCreateTable);
            preparedStatement.executeUpdate(sqlInsert);
            preparedStatement.setBlob(1, blob);
            preparedStatement.executeUpdate();
            connection.commit();
        }
    }*/
}
