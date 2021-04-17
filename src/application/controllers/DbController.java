package application.controllers;

import application.models.User;

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
    private Connection connection = null;
    public static final String JDBC_URL = "jdbc:sqlite::memory:";   /* in-memory */
    public static final String JDBC_URL_2 = "jdbc:sqlite:./src/application/database/noMoreDementiaDB.db";   /* physical database */
    private static final String CREATE_USERS_TABLE_STATEMENT = "create table users_table (name text, email text not null primary key, password text)";
    private static final String INSERT_INTO_USERS_TABLE_SQL = "insert into users_table(name, email, password) values(?, ?, ?)";

    /** Private constructor to restrict creating new instances.
     * Upon instantiate, the instance:
     * (1) connects to the db
     * (2) creates the user_table
     * */
    private DbController() {
        connect();
        createTable(CREATE_USERS_TABLE_STATEMENT);
    }

    /** Good old getter */
    public static DbController getSingleDBInstance() {
        return singleDBInstance;
    }

    /** This function makes a connection to the SQLite DB using the SQLite JDBC driver. */
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(JDBC_URL);
            System.out.println("Connection to SQLite success.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This function closes the connection to the database.
     */
    public void closeDB() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("DB close success");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Create a new table in the in-memory database.
     * Bug: "DBNavigator" causes SQL statement to be unrecognized.
     * Fix: Disable/Uninstall DBNavigator.
     *
     * @param SqlStatement sql statement
     */
    public void createTable(String SqlStatement) {
        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.execute(SqlStatement);
            statement.close();
            System.out.println("Create table success: " + SqlStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Insert into users_table.
     * Current issue: make email as primary key causes insert to fail.
     *
     * @param user to insert into table
     */
    public boolean insertIntoUsersTable(User user) {
        PreparedStatement ps = null;
        try {
            connection.setAutoCommit(false);
            ps = this.connection.prepareStatement(INSERT_INTO_USERS_TABLE_SQL);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
            ps.close();
            connection.commit();
            System.out.println("Insert into users_table success.");
            return true;
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
            return false;
        }
    }


    /**
     * This function pulls all attributes from the db that matches 'email' attribute.
     * The main purpose is to check whether the email exists in the db.
     *
     * @param email email provided in the login screen
     * @return user if found, null if user doesn't exist in db
     */
    public User selectUserFromUsersTable(String email) {
        User user = null;

        Statement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users_table where email=\"" + email +"\";");


            String name = rs.getString("name");
            String password = rs.getString("password");

            User us = new User(name, email, password);
            System.out.println(us);

            rs.close();
            statement.close();
            connection.commit();
            System.out.println("Select from users_table success.");

            user = new User(name, email, password);
            return user;
        } catch (SQLException e) {
            System.out.println("Select error: " + e.getMessage());
            return null;
        }
    }

    /**
     * This main method is main used to test the database queries.
     * @param args
     */
    public static void main(String[] args) {
        DbController dbInstance = DbController.getSingleDBInstance();

        User user = new User("name", "email", "address");
        User user1 = new User("name1", "email1", "address1");
        User user2 = new User("name1", "email1", "address1");


        dbInstance.insertIntoUsersTable(user);
        dbInstance.insertIntoUsersTable(user1);
        dbInstance.insertIntoUsersTable(user2); /* expect error: duplicate email */


        dbInstance.closeDB();
    }
}
