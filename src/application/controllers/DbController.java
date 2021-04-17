package application.controllers;

import java.sql.*;

/**
 * This class controls all the interactions between the application and the database.
 * The underlying database is SQLite3.
 * This class applies the Singleton pattern under the hood.
 * Other classes can access it statically like this:
 *      DbController instance = DbController.getSingleDBInstance();
 * */

public class DbController {
    private static DbController singleDBInstance = new DbController();
    private static Connection connection = null;
    public static final String JDBC_URL = "jdbc:sqlite::memory:";   /* in-memory */
    private static final String DB_NAME = "users.db";
    private static final String USERS_TABLE = "users_table";
    private static final String COL_NAME = "name";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    public static final String CREATE_USERS_TABLE_STATEMENT = "create table " + USERS_TABLE + " (" +
            COL_NAME + " text, " +
            COL_EMAIL + " text primary key, " +
            COL_PASSWORD + " text)";

    /** Private constructor to restrict creating new instances. */
    private DbController() {}

    /** Good old getter */
    public static DbController getSingleDBInstance() {
        return singleDBInstance;
    }

    /** This function makes a connection to the SQLite DB using the SQLite JDBC driver. */
    public static void connect() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This function closes the connection to the database.
     */
    public static void closeDB() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("DB close successfully!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Create a new table in the in-memory database.
     * Bug: "DBNavigator" causes SQL statement to be unrecognized.
     * Fix: Disable/Uninstall DBNavigator.
     */
    public static void createTable(String SqlStatement) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(SqlStatement);
            System.out.println("Create table successful: " + SqlStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        connect();
        createTable(CREATE_USERS_TABLE_STATEMENT);

        closeDB();
    }
}
