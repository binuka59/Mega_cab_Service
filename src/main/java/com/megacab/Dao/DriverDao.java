package com.megacab.Dao;

import com.megacab.model.AdminDriver;
import com.megacab.model.Driver;


import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDao {
    public static List<Driver> getAllDrivers() {
        List<Driver> driverList = new ArrayList<>();
        String query = "SELECT l.Name AS name, l.email AS email, l.mobile AS mobile, l.nic AS nic, " +
                "d.Dimage AS image, d.status AS status, d.id AS id " +
                "FROM login l " +
                "JOIN driver d ON l.id = d.Did " +
                "WHERE l.status = ?";

        try {
            Connection connection = DbConnectionFactory.getConnection();
            PreparedStatement statement1 = connection.prepareStatement(query);
            statement1.setString(1, "Driver");
            ResultSet resultSet = statement1.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String fileName = resultSet.getString("image");
                String uploadPath = "assets/user/img/driver/";
                String image = uploadPath + fileName;

                String name = resultSet.getString("name");
                String nic = resultSet.getString("nic");
                String mobile = resultSet.getString("mobile");
                String email = resultSet.getString("email");
                String status = resultSet.getString("status");

                driverList.add(new Driver(id, image, name, nic, mobile, email, status));
            }

            // Close resources
            resultSet.close();
            statement1.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driverList;
    }

    public static void addData(AdminDriver driver) {
        String query = "INSERT INTO login(Name, email, password, mobile, nic, status) VALUES(?, ?, ?, ?, ?, ?)";
        String driquery = "INSERT INTO driver(Dimage, Did, status) VALUES(?, ?, ?)";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             PreparedStatement statement1 = connection.prepareStatement("SELECT id FROM login WHERE email = ?")) {

            // Step 1: Insert data into `login` table
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getEmail());
            statement.setString(3, driver.getPassword());
            statement.setString(4, driver.getPhone());
            statement.setString(5, driver.getNic());
            statement.setString(6, "Driver");

            statement.executeUpdate();


            statement1.setString(1, driver.getEmail());
            ResultSet resultSet = statement1.executeQuery();
            Integer uid = null;

            if (resultSet.next()) {
                uid = resultSet.getInt("id");
            }


            resultSet.close();


            if (uid != null) {
                try (PreparedStatement statement2 = connection.prepareStatement(driquery)) {
                    statement2.setString(1, driver.getFilename());
                    statement2.setInt(2, uid);
                    statement2.setString(3, "Active");

                    statement2.executeUpdate();
                }
            } else {
                System.out.println("Error: No user ID found for email " + driver.getEmail());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Driver> getAllDriversDetails(String deditid) {
        List<Driver> DriverListSelect = new ArrayList<>();

        String query = "SELECT l.Name AS name, l.email AS email, l.mobile AS mobile, l.nic AS nic, " +
                "d.Dimage AS image, d.status AS status, d.id AS id " +
                "FROM login l " +
                "JOIN driver d ON l.id = d.Did " +
                "WHERE l.status = ? AND d.id=?";

        try {
            Connection connection = DbConnectionFactory.getConnection();
            PreparedStatement statement1 = connection.prepareStatement(query);
            statement1.setString(1, "Driver");
            statement1.setString(2,deditid);
            ResultSet resultSet = statement1.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String fileName = resultSet.getString("image");
                String uploadPath = "assets/user/img/driver/";
                String image = uploadPath + fileName;

                String name = resultSet.getString("name");
                String nic = resultSet.getString("nic");
                String mobile = resultSet.getString("mobile");
                String email = resultSet.getString("email");
                String status = resultSet.getString("status");

                DriverListSelect.add(new Driver(id, image, name, nic, mobile, email, status));
            }


            resultSet.close();
            statement1.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DriverListSelect;

    }

    public static void updateData(Driver driver) {
        String query1 = "UPDATE login l " +
                "SET l.Name = ?, l.email = ?, l.mobile = ?, l.nic = ? " +
                "WHERE l.id = (SELECT d.Did FROM driver d WHERE d.id = ?)";

        String query2 = "UPDATE driver d " +
                "SET d.Dimage = ? " +
                "WHERE d.id = ?";

        try (Connection connection = DbConnectionFactory.getConnection()) {

            // Update the login table
            try (PreparedStatement statement1 = connection.prepareStatement(query1)) {
                statement1.setString(1, driver.getName());
                statement1.setString(2, driver.getEmail());
                statement1.setString(3, driver.getPhone());
                statement1.setString(4, driver.getNic());
                statement1.setInt(5, driver.getId());
                statement1.executeUpdate();
            }

            // Update the driver table (for Dimage)
            try (PreparedStatement statement2 = connection.prepareStatement(query2)) {
                statement2.setString(1, driver.getFilename());
                statement2.setInt(2, driver.getId());
                statement2.executeUpdate();
            }

        } catch (SQLException e) {
            // Log and rethrow the exception
            e.printStackTrace();
            throw new RuntimeException("Error executing update queries", e);
        }

    }
}
