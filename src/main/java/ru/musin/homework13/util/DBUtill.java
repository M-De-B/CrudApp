package ru.musin.homework13.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtill {

    private static Connection connection;

    public static Connection createConnection() {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/database.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                    properties.getProperty("database.url"),
                    properties.getProperty("database.username"),
                    properties.getProperty("database.password")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
