package ac.ku.Lorna.dao;

import ac.ku.Lorna.models.User;

import java.sql.*;

public class UsersDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/fedha_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Lorna_2005";

    // SQL query to authenticate user
    private static final String SELECT_USER_SQL = "SELECT * FROM Users WHERE BINARY username = ? AND BINARY password = ?";

    // SQL query to insert a new user
    private static final String INSERT_USER_SQL = "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)";

    // Method to authenticate a user
    public User authenticateUser(String username, String password) {
        System.out.println("Authenticating user: " + username);

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL)) {

            preparedStatement.setString(1, username.trim()); // Use .trim() to avoid leading/trailing spaces
            preparedStatement.setString(2, password.trim()); // Use .trim() to avoid leading/trailing spaces

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("User authenticated: " + resultSet.getString("username"));
                return new User(
                        resultSet.getLong("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("role")
                );
            } else {
                System.out.println("No user found with the provided credentials.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }

        return null; // Return null if user is not found
    }

    // Method to register a new user
    public boolean registerUser(String username, String password, String role) {
        System.out.println("Registering new user: " + username);

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {

            preparedStatement.setString(1, username.trim()); // Ensure no leading/trailing spaces
            preparedStatement.setString(2, password.trim());
            preparedStatement.setString(3, role.trim());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User registered successfully!");
                return true;
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // MySQL error code for duplicate entry
                System.err.println("Error: Username already exists.");
            } else {
                System.err.println("SQL Exception occurred: " + e.getMessage());
            }
            e.printStackTrace();
        }

        return false; // Return false if registration failed
    }

    // Method to test database connection and list users (for debugging)
    public static void testConnection() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Users")) {

            System.out.println("Listing all users from the database:");
            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username") + ", Password: " + rs.getString("password"));
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Main method for testing the connection and registration
    public static void main(String[] args) {
        UsersDAO dao = new UsersDAO();
        dao.testConnection();

        // Test registration
        dao.registerUser("new_user", "password123", "User");
    }
}
