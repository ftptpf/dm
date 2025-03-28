package ru.ftptpf.meta;

import ru.ftptpf.util.ConnectionManager;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MetaDateRunner {

    public static void main(String[] args) throws SQLException {
        checkMetaDate();
    }

    private static void checkMetaDate() throws SQLException {
        try (Connection connection = ConnectionManager.open()) {
            DatabaseMetaData metaData = connection.getMetaData();

            ResultSet catalogs = metaData.getCatalogs();
            while (catalogs.next()) {
                String catalog = catalogs.getString("TABLE_CAT");
                System.out.println("Catalog name: " + catalog);

                ResultSet schemas = metaData.getSchemas();
                while (schemas.next()) {
                    String schema = schemas.getString("TABLE_SCHEM");
                    System.out.println("-- Schema name: " + schema);

                    ResultSet tables = metaData.getTables(catalog, schema, null, new String[]{"TABLE"});
                    if (schema.equals("public")) {
                        while (tables.next()) {
                            System.out.println("----- Table name: " + tables.getString("TABLE_NAME"));
                        }
                    }
                }
            }
        }
    }
}
