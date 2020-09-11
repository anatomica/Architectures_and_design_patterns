package Homework_6.DataMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static Connection connection;

    private DBManager() {}

    public static Connection connection() throws SQLException  {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:sqlite::resource:main.db");
        }
        return connection;
    }

    public static void disconnect() throws SQLException {
        if (!connection.isClosed() && connection != null) {
            connection.close();
        }
    }
}
