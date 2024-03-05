/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DBContext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MobileDAO extends DBContext {

    private Connection connection;

    public MobileDAO() {
        this.connection = super.connection;
    }

    // Example method to retrieve all mobiles from the database
    public List<Mobile> getAllMobiles() {
        List<Mobile> mobiles = new ArrayList<>();

        try {
            String query = "SELECT * FROM Mobiles";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Mobile mobile = new Mobile(
                        resultSet.getString("mobileId"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getString("mobileName"),
                        resultSet.getInt("yearOfProduction"),
                        resultSet.getInt("quantity"),
                        resultSet.getBoolean("notSale")
                );

                mobiles.add(mobile);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mobiles;
    }

    public void createMobile(Mobile mobile) {
        try {
            String query = "INSERT INTO Mobiles VALUES (?, ?, ?, ?, ?, ?, ?)";
            try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, mobile.getMobileId());
                preparedStatement.setString(2, mobile.getDescription());
                preparedStatement.setDouble(3, mobile.getPrice());
                preparedStatement.setString(4, mobile.getMobileName());
                preparedStatement.setInt(5, mobile.getYearOfProduction());
                preparedStatement.setInt(6, mobile.getQuantity());
                preparedStatement.setBoolean(7, mobile.isNotSale());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// Inside MobileDAO class

    public List<Mobile> getMobilesByPriceRange(double minPrice, double maxPrice) {
        List<Mobile> mobiles = new ArrayList<>();

        try {
            String query = "SELECT * FROM Mobiles WHERE price BETWEEN ? AND ?";
            try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setDouble(1, minPrice);
                preparedStatement.setDouble(2, maxPrice);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Mobile mobile = new Mobile(
                            resultSet.getString("mobileId"),
                            resultSet.getString("description"),
                            resultSet.getDouble("price"),
                            resultSet.getString("mobileName"),
                            resultSet.getInt("yearOfProduction"),
                            resultSet.getInt("quantity"),
                            resultSet.getBoolean("notSale")
                    );

                    mobiles.add(mobile);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mobiles;
    }

    public Mobile getMobileById(String mobileId) {
        Mobile mobile = null;

        try {
            String query = "SELECT * FROM Mobiles WHERE mobileId = ?";
            try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, mobileId);

                try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        mobile = new Mobile(
                                resultSet.getString("mobileId"),
                                resultSet.getString("description"),
                                resultSet.getDouble("price"),
                                resultSet.getString("mobileName"),
                                resultSet.getInt("yearOfProduction"),
                                resultSet.getInt("quantity"),
                                resultSet.getBoolean("notSale")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mobile;
    }

    // Update an existing mobile in the database
    public void updateMobile(Mobile mobile) {
        try {
            String query = "UPDATE Mobiles SET description=?, price=?, mobileName=?, yearOfProduction=?, quantity=?, notSale=? WHERE mobileId=?";
            try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, mobile.getDescription());
                preparedStatement.setDouble(2, mobile.getPrice());
                preparedStatement.setString(3, mobile.getMobileName());
                preparedStatement.setInt(4, mobile.getYearOfProduction());
                preparedStatement.setInt(5, mobile.getQuantity());
                preparedStatement.setBoolean(6, mobile.isNotSale());
                preparedStatement.setString(7, mobile.getMobileId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a mobile from the database
    public void deleteMobile(String mobileId) {
        try {
            String query = "DELETE FROM Mobiles WHERE mobileId=?";
            try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, mobileId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        MobileDAO dao = new MobileDAO();

        // Example to get all mobiles
        System.out.println(dao.getAllMobiles());

        dao.deleteMobile("IPX");
    }
}
