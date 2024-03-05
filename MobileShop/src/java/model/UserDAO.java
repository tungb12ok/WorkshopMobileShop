package model;

import DBContext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DBContext {

    private Connection connection;

    public UserDAO() {
        this.connection = super.connection;
    }

    // User login method
    public User login(String userId, String password) {
        try {
            String query = "SELECT * FROM Users WHERE userId = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return new User(
                                resultSet.getString("userId"),
                                resultSet.getString("password"),
                                resultSet.getString("fullname"),
                                resultSet.getInt("role")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if login fails
    }

    // Create a new user in the database
    public void createUser(User user) {
        try {
            String query = "INSERT INTO Users VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, user.getUserId());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getFullname());
                preparedStatement.setInt(4, user.getRole());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all users from the database
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            String query = "SELECT * FROM Users";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    User user = new User(
                            resultSet.getString("userId"),
                            resultSet.getString("password"),
                            resultSet.getString("fullname"),
                            resultSet.getInt("role")
                    );

                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    // Update an existing user in the database
    public void updateUser(User user) {
        try {
            String query = "UPDATE Users SET password=?, fullname=?, role=? WHERE userId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, user.getPassword());
                preparedStatement.setString(2, user.getFullname());
                preparedStatement.setInt(3, user.getRole());
                preparedStatement.setString(4, user.getUserId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a user from the database
    public void deleteUser(String userId) {
        try {
            String query = "DELETE FROM Users WHERE userId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, userId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();

        // Example to get all users
        System.out.println(dao.getAllUsers());

        // Example to create a new user

        // Example to login
        User loggedInUser = dao.login("ExistingUser", "123");
        System.out.println("Logged in user: " + loggedInUser);
    }
}
