package com.megacab.Dao;

import com.megacab.model.AdminBook;
import com.megacab.model.Booking;
//import com.megacab.model.Vehicle;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class BookingDao {


    public static void addData(Booking booking) {
        LocalDate today = LocalDate.now();
        Date sqlDate = Date.valueOf(today);

        String vehicletype = booking.getVehicletype();
        String query = "SELECT * FROM vehicle WHERE v_name=?";

        try {
            Connection connection = DbConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, vehicletype);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Integer VId = resultSet.getInt("vehicleid");


                String query1 = "INSERT INTO booking(uid, vid, driver, pickdate, picktime, pickaddress, dropaddress, date) VALUES(?,?,?,?,?,?,?,?)";
                PreparedStatement statement1 = connection.prepareStatement(query1);

                statement1.setInt(1, booking.getId());
                statement1.setInt(2, VId);
                statement1.setString(3, booking.getDriver());
                statement1.setString(4, booking.getPickupdate());
                statement1.setString(5, booking.getPicktime());
                statement1.setString(6, booking.getPickaddress());
                statement1.setString(7, booking.getDropaddress());
                statement1.setDate(8, sqlDate);

                statement1.executeUpdate();
            }

            resultSet.close();
            statement.close();
            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Booking> getAllBooking() {
        List<Booking> BookingList = new ArrayList<>();
        LocalDate today = LocalDate.now();
        Date todayDate = Date.valueOf(today);

        String query = "SELECT L.Name AS name, v.v_name AS vehicle, " +
                "B.bid AS id ,B.pickaddress AS pickaddress, B.dropaddress AS dropaddress, B.picktime AS picktime  , B.pickdate AS pickdate " +
                "FROM booking B " + "JOIN vehicle v ON B.vid = v.vehicleid " + "JOIN login L ON B.uid = L.id " +
                "WHERE B.pickdate = ?";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDate(1, todayDate);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer id =resultSet.getInt("id");
                System.out.println("id is"+id);
                String name = resultSet.getString("name");
                String vehicle = resultSet.getString("vehicle");
                String time = resultSet.getString("picktime");
                String date = resultSet.getString("pickdate");
                String pickaddress = resultSet.getString("pickaddress");
                String dropaddress = resultSet.getString("dropaddress");

                BookingList.add(new Booking(id,name, vehicle, time, pickaddress, dropaddress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BookingList;
    }

    public List<AdminBook> getAllBookingdetails() {
        List<AdminBook> BookingList = new ArrayList<>();
        LocalDate today = LocalDate.now();
        Date todayDate = Date.valueOf(today);

        String query = "SELECT L.Name AS name, v.v_name AS vehicle, " +
                "B.bid AS id , B.pickaddress AS pickaddress, B.dropaddress AS dropaddress, B.picktime AS picktime  ,B.pickdate AS pickdate "+
                "FROM booking B " + "JOIN vehicle v ON B.vid = v.vehicleid " + "JOIN login L ON B.uid = L.id ";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String vehicletype = resultSet.getString("vehicle");
                String time = resultSet.getString("picktime");
                String Date = resultSet.getString("pickdate");
                String pickaddress = resultSet.getString("pickaddress");
                String dropaddress = resultSet.getString("dropaddress");

                BookingList.add(new AdminBook(id,name, vehicletype, time,Date, pickaddress, dropaddress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BookingList;
    }

    public List<AdminBook> getbookdetails(String seeid) {
        List<AdminBook> BookSelect = new ArrayList<>();
        String query = "SELECT L.Name AS name, L.email AS email, L.mobile AS mobile, L.nic AS nic, " +
                "v.v_name AS vehicle, v.v_price AS price, v.v_estimation AS estimate,v.v_addtionalprice AS additional," +
                "B.bid AS id ,B.driver AS driver, B.pickaddress AS pickaddress, B.dropaddress AS dropaddress, B.picktime AS picktime, " +
                "B.pickdate AS pickdate " +
                "FROM booking B " +
                "JOIN vehicle v ON B.vid = v.vehicleid " +
                "JOIN login L ON B.uid = L.id " +
                "WHERE B.bid = ?";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, seeid);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String mobile = resultSet.getString("mobile");
                    String nic = resultSet.getString("nic");
                    String driver = resultSet.getString("driver");
                    String vehicletype = resultSet.getString("vehicle");
                    String price = resultSet.getString("price");
                    String estimate = resultSet.getString("estimate");
                    String additional = resultSet.getString("additional");
                    String time = resultSet.getString("picktime");
                    String date = resultSet.getString("pickdate");
                    String pickaddress = resultSet.getString("pickaddress");
                    String dropaddress = resultSet.getString("dropaddress");

                    BookSelect.add(new AdminBook(id,name, email, mobile, nic, driver,vehicletype, price, estimate,additional, time, date, pickaddress, dropaddress));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return BookSelect;

    }
}
