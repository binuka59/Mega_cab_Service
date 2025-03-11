package com.megacab.Dao;

import com.megacab.model.Booking;
import com.megacab.model.Login;
import com.megacab.model.Payment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao {
    public static void addpayment(Payment payment)
    {
    String bookid="";
    try {Connection Connection = DbConnectionFactory.getConnection();
        Integer bid = payment.getId();
        LocalDate today = LocalDate.now();
        Date sqlDate = Date.valueOf(today);

        String query = "SELECT * FROM booking WHERE uid=? ORDER BY bid DESC LIMIT 1";
        try (PreparedStatement statement1 = Connection.prepareStatement(query)) {
            statement1.setInt(1, bid);
            ResultSet resultSet = statement1.executeQuery();
                if (resultSet.next()) {
                     bookid = resultSet.getString("bid");
                    System.out.println("Last Inserted Booking ID: " + bookid);
                }

        }

        String query1 = "INSERT INTO payment (price,date,bid,status) VALUES (?,?,?,?)";

        try(PreparedStatement statement = Connection.prepareStatement(query1)){
            statement.setDouble(1, payment.getBalance());
            statement.setDate(2, sqlDate);
            statement.setString(3, bookid);
            statement.setString(4, "Pending");

            statement.executeUpdate();
        }
    } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public static void deletebook(Payment payment) {

            String query = "DELETE FROM payment WHERE bid=?";

            Integer payid = payment.getId();


            try (Connection connection = DbConnectionFactory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, payid);
                int rowsDeleted = statement.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Payment deleted successfully for bid: " + payid);
                } else {
                    System.out.println("No payment found with bid: " + payid);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error deleting payment: " + e.getMessage());
            }
    }

    public static void updatepayment(Payment payment) {
        Integer id = payment.getId();
        LocalDate today = LocalDate.now();
        Date sqlDate = Date.valueOf(today);
        String query = "UPDATE payment SET price=? ,date=? ,status=?  WHERE bid=?";

        try {

            Connection connection = DbConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1,payment.getAmount());
            statement.setDate(2, sqlDate);
            statement.setString(3, "Pay");
            statement.setInt(4, id);

            statement.executeUpdate();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Payment> getmypayment(Integer userId) {
        List<Payment> paymentList  = new ArrayList<>();

        String query = "SELECT v.v_price AS price, v.v_addtionalprice AS addition, v.v_estimation AS estimate, " +
                "B.bid AS bid, B.pickaddress AS pickaddress, B.dropaddress AS dropaddress, B.pickdate AS pickdate, B.driver AS driver " +
                "FROM booking B " +
                "JOIN vehicle v ON B.vid = v.vehicleid " +
                "JOIN login L ON B.uid = L.id " +
                "WHERE B.uid = ?";


        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String price = resultSet.getString("price");
                String bid = resultSet.getString("bid");
                String addition = resultSet.getString("addition");
                String estimate = resultSet.getString("estimate");
                String pickaddress = resultSet.getString("pickaddress");
                String dropaddress = resultSet.getString("dropaddress");

                Date pickdateObj = resultSet.getDate("pickdate");
                String pickdate = (pickdateObj != null) ? pickdateObj.toString() : "N/A"; // Handle null dates

                String driver = resultSet.getString("driver");

                paymentList.add(new Payment(bid, price, addition, estimate, pickdate, driver, pickaddress, dropaddress));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching payment details: " + e.getMessage());
            e.printStackTrace();
        }

        return paymentList;

    }
}
