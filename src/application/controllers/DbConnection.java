package application.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public static void connection() {
        /* files
        * String jdbcUrl = "jdbc:sqlite::memory:./src/application/database/noMoreDementiaDB.db";
        * */

        /* in-memory */
        String jdbcUrl = "jdbc:sqlite::memory:";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcUrl);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeDB(connection);
        }
    }

    public static void closeDB(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("DB close successfully!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        connection();
    }
}
