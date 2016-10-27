package edu.avans.library.datastorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A wrapper class to make database actions easier to write.
 */
public class DatabaseConnection {
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(DatabaseConnection.class.getName());
    }

    private final String connectionString;
    private final String user;
    private final String password;
    private Connection connection;

    /**
     * Initializes a new DatabaseConnection instance using the specified connection string.
     *
     * @param connectionString The connection string to create the connection with.
     * @param user             The user to create the connection with.
     * @param password         The password to create the connection with.
     */
    public DatabaseConnection(String connectionString, String user, String password) {
        this.connectionString = connectionString;
        this.user = user;
        this.password = password;
    }

    /**
     * Opens the connection.
     *
     * @return true if successful; otherwise, false.
     */
    public boolean open() {
        if (isOpen()) {
            return true;
        }

        try {
            connection = DriverManager.getConnection(connectionString, user, password);
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while trying to open the database connection.", e);
            return false;
        }
    }

    /**
     * Checks if the connection is currently open.
     *
     * @return true if open; otherwise, false.
     */
    public boolean isOpen() {
        if (connection == null) {
            return false;
        }

        try {
            return !connection.isClosed();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while trying to check if the database connection is open.", e);
            return false;
        }
    }

    /**
     * Closes the connection.
     *
     * @return true if successful; otherwise, false.
     */
    public boolean close() {
        if (!isOpen()) {
            return true;
        }

        try {
            connection.close();
            connection = null;
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while trying to close the database connection.", e);
            return false;
        }
    }

    /**
     * Executes SQL that does not return any results.
     *
     * @param sql The sql to be executed
     * @return true if successful; otherwise, false.
     */
    public boolean execute(String sql) {
        try {
            Statement statement = connection.createStatement();
            return statement.execute(sql);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while trying to execute the statement.", e);
            return false;
        }
    }

    /**
     * Executes SQL that does not return any results.
     *
     * @param sql The sql to be executed
     * @return true if successful; otherwise, false.
     */
    public int[] executeBatch(String... sql) {
        try {
            Statement statement = connection.createStatement();
            for (String sqlLine : sql) {
                statement.addBatch(sqlLine);
            }
            return statement.executeBatch();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while trying to execute the statement.", e);
            return new int[0];
        }
    }

    /**
     * Executes an SQL query that returns results.
     *
     * @param sql The sql to be executed
     * @return The result set returned by the query.
     */
    public ResultSet executeQuery(String sql) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while trying to execute the query.", e);
            return null;
        }
    }

    /**
     * Creates and executes an SQL string by placing the specified items into the SQL format string and returns the generated keys.
     *
     * @param sqlFormat The SQL string to place the items in.
     * @param items     The items to place into the SQL format string.
     * @return The generated keys as an integer List.
     */
    public List<Integer> executePrepared(String sqlFormat, Object... items) {
        try {
            PreparedStatement statement = connection.prepareStatement(sqlFormat, Statement.RETURN_GENERATED_KEYS);

            int itemCount = items.length;

            for (int i = 0; i < itemCount; i++) {
                statement.setObject(i + 1, items[i]);
            }

            statement.execute();

            List<Integer> generatedIds = new ArrayList<>();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                generatedIds.add(generatedKeys.getInt(1));
            }

            return generatedIds;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while trying to execute the prepared statement.", e);
            return new ArrayList<>();
        }
    }

    /**
     * Creates and executes an SQL string for each set of items in the specified batch by placing the specified items into the SQL format string and returns the number of affected rows.
     *
     * @param sqlFormat The SQL string to place the items in.
     * @param batch     The batch with item collections to place in the SQL string.
     * @return The number of affected rows as an integer List.
     */
    public int[] executePreparedBatch(String sqlFormat, List<Object[]> batch) {
        try {
            PreparedStatement statement = connection.prepareStatement(sqlFormat);

            for (Object[] items : batch) {
                int itemCount = items.length;

                for (int i = 0; i < itemCount; i++) {
                    statement.setObject(i + 1, items[i]);
                }

                statement.addBatch();
            }

            return statement.executeBatch();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while trying to execute the prepared statement batch.", e);
            return new int[0];
        }
    }
}