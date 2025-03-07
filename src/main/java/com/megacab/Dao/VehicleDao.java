package com.megacab.Dao;

import com.megacab.model.Vehicle;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    public static List<Vehicle> getAllVehicle() {
        List<Vehicle> Vehicle = new ArrayList<>();
        String query = "SELECT * FROM vehicledetail ";

        try {
            Connection Connection = DbConnectionFactory.getConnection();
            Statement statement1 = Connection.createStatement();
            ResultSet resultSet = statement1.executeQuery(query);

            while (resultSet.next()) {
                String fileName = resultSet.getString("Image");
                String uploadPath = "assets/user/img/"; // Folder inside web directory
                String image = uploadPath + File.separator + fileName;

                String name = resultSet.getString("type");
                double initial = resultSet.getDouble("initial");
                double fee = resultSet.getDouble("fee");


                Vehicle.add(new Vehicle(name,image, initial, fee));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Vehicle;
    }


    public static List<Vehicle> getAllVehicletype(String vehiclename) {
        List<Vehicle> vehicletype = new ArrayList<>();
        String query = "SELECT v.v_image, v.v_name, v.v_price,v_addtionalprice , v_estimation, v.status " +
                "FROM vehicle v " + "JOIN vehicledetail vd ON v.details_id = vd.id " +
                "WHERE vd.type = ?";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, vehiclename);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String fileName = resultSet.getString("v_image");
                String uploadPath = "assets/user/img/"; // Folder inside web directory
                String image = uploadPath + File.separator + fileName;


                String vname = resultSet.getString("v_name");
                double price = resultSet.getDouble("v_price");
                String addtionalprice = resultSet.getString("v_addtionalprice");
                String estimation = resultSet.getString("v_estimation");
                String status = resultSet.getString("status");

                vehicletype.add(new Vehicle(image,vname, price, addtionalprice ,estimation,status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicletype;
    }

    public static void addData(Vehicle vehicle)
    {
        String query = "SELECT id FROM vehicledetail WHERE type=?";
        String insertQuery = "INSERT INTO vehicle (v_image, v_name,v_price,v_addtionalprice , v_estimation ,status, details_id) VALUES (?, ?, ? , ? ,? ,? ,?)";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {


            preparedStatement.setString(1, vehicle.getType());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");


                    try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                        insertStatement.setString(1, vehicle.getFilename());
                        insertStatement.setString(2, vehicle.getName());
                        insertStatement.setDouble(3, vehicle.getPrice());
                        insertStatement.setString(4, vehicle.getAdditionalprice());
                        insertStatement.setString(5, vehicle.getEstimate());
                        insertStatement.setString(6, vehicle.getStatus());
                        insertStatement.setInt(7, id);

                        insertStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void updateData(Vehicle vehicle1) {
        String  vehicletype = vehicle1.getType();
        String status="Pending";
        String query = "UPDATE vehicle SET status=? WHERE v_name=?";

        try {

            Connection connection = DbConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, status);
            statement.setString(2,vehicletype);

            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Vehicle> getdisplayvehicle()
    {
        List<Vehicle> vehicleLists = new ArrayList<>();


        String query = "SELECT v.vehicleid , v.v_image, v.v_name, v.v_price,v_addtionalprice , v_estimation, v.status " +
                "FROM vehicle v " + "JOIN vehicledetail vd ON v.details_id = vd.id ";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String fileName = resultSet.getString("v_image");
                String uploadPath = "assets/user/img/"; // Folder inside web directory
                String image = uploadPath + File.separator + fileName;

                int id = resultSet.getInt("vehicleid");
                String vname = resultSet.getString("v_name");
                double price = resultSet.getDouble("v_price");
                String addtionalprice = resultSet.getString("v_addtionalprice");
                String estimation = resultSet.getString("v_estimation");
                String status = resultSet.getString("status");


                vehicleLists.add(new Vehicle(id,image,vname, price, addtionalprice ,estimation,status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleLists;
    }


    public static List<Vehicle> getVehicleDetail(String editid) {
        List<Vehicle> VehicledetailList = new ArrayList<>();


        String query = "SELECT v.vehicleid , v.v_image, v.v_name, v.v_price,v_addtionalprice , v_estimation, v.status ,vd.type AS vehicle_type " +
                "FROM vehicle v " + "JOIN vehicledetail vd ON v.details_id = vd.id  WHERE v.vehicleid=?";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,editid);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String fileName = resultSet.getString("v_image");
                String uploadPath = "assets/user/img/"; // Folder inside web directory
                String image = uploadPath + File.separator + fileName;


                String vname = resultSet.getString("v_name");
                double price = resultSet.getDouble("v_price");
                String addtionalprice = resultSet.getString("v_addtionalprice");
                String estimation = resultSet.getString("v_estimation");
                String type = resultSet.getString("vehicle_type");
                String status = resultSet.getString("status");


                VehicledetailList.add(new Vehicle(image,vname,price,addtionalprice,estimation,type,status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return VehicledetailList;
    }

    public static void updateVehicleData(Vehicle vehicle) {
        String query = "SELECT id FROM vehicledetail WHERE type=?";

        String upquery = "UPDATE vehicle SET v_image=?, v_name=?, v_price=?, v_addtionalprice=?, v_estimation=?, status=?, details_id=? WHERE vehicleid=?";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, vehicle.getType());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");

                try (PreparedStatement statement = connection.prepareStatement(upquery)) {
                    statement.setString(1, vehicle.getFilename());
                    statement.setString(2, vehicle.getName());
                    statement.setDouble(3, vehicle.getPrice());
                    statement.setString(4, vehicle.getAdditionalprice());
                    statement.setString(5, vehicle.getEstimate());
                    statement.setString(6, vehicle.getStatus());
                    statement.setInt(7, id);
                    statement.setInt(8, vehicle.getId());

                    // Debug prints
                    System.out.println("Name: " + vehicle.getName());
                    System.out.println("File Name: " + vehicle.getFilename());
                    System.out.println("Price: " + vehicle.getPrice());
                    System.out.println("Additional Price: " + vehicle.getAdditionalprice());
                    System.out.println("Estimate: " + vehicle.getEstimate());
                    System.out.println("Vehicle Type: " + vehicle.getType());
                    System.out.println("Status: " + vehicle.getStatus()); // Fix: Correctly print status

                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
