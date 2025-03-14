package com.megacab.Dao;

import com.megacab.model.AdminBook;
import com.megacab.model.Booking;
import com.megacab.model.Payment;
//import com.megacab.model.Vehicle;

import java.awt.datatransfer.DataFlavor;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class BookingDao {


    private DataFlavor request;

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


                String query1 = "INSERT INTO booking(uid, vid, driver, pickdate, picktime, pickaddress, dropaddress, date,status) VALUES(?,?,?,?,?,?,?,?,?)";
                PreparedStatement statement1 = connection.prepareStatement(query1);

                statement1.setInt(1, booking.getId());
                statement1.setInt(2, VId);
                statement1.setString(3, booking.getDriver());
                statement1.setString(4, booking.getPickupdate());
                statement1.setString(5, booking.getPicktime());
                statement1.setString(6, booking.getPickaddress());
                statement1.setString(7, booking.getDropaddress());
                statement1.setDate(8, sqlDate);
                statement1.setString(9, "Accept");

                statement1.executeUpdate();
            }

            resultSet.close();
            statement.close();
            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static List<Booking> getdriverbook(Integer userId) {
        List<Booking> DriverList = new ArrayList<>();
        System.out.println("driver id eka"+userId);
        String query = "SELECT B.bid AS id ,B.uid AS uid ,B.pickaddress AS pickaddress, B.dropaddress AS dropaddress, B.picktime AS picktime, B.pickdate AS pickdate " +
                "FROM booking B " +
                "JOIN vehicle V ON B.vid = V.vehicleid " +
                "JOIN login L ON B.uid = L.id " +
                "JOIN driver D ON V.vehicleid = D.vehicle_id " +
                "WHERE D.Did = ? AND B.status = ? AND B.driver = ?";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1,userId);
            preparedStatement.setString(2,"Accept");
            preparedStatement.setString(3,"With");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer id =resultSet.getInt("id");
                System.out.println("id eka"+id);
                Integer uid =resultSet.getInt("uid");
                System.out.println("uid eka"+uid);
                String time = resultSet.getString("picktime");
                Date date = resultSet.getDate("pickdate");
                String pickaddress = resultSet.getString("pickaddress");
                String dropaddress = resultSet.getString("dropaddress");
                String uname="";
                String query1 = "SELECT Name FROM login WHERE id=?";
                try{PreparedStatement st = connection.prepareStatement(query1);
                    st.setInt(1,uid);
                    ResultSet rs = st.executeQuery();
                    if (rs.next())
                    {
                       uname = rs.getString("Name");
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                DriverList .add(new Booking(id,uname, time,date, pickaddress, dropaddress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DriverList ;

    }

    public static List<Payment> getpaybooking(Integer dpid) {
        List<Payment> BookingList = new ArrayList<>();
        String query = "SELECT L.email AS email, " +
                "B.bid AS id ,B.pickaddress AS pickaddress, B.dropaddress AS dropaddress," +
                "v.v_price AS price ,v.v_addtionalprice AS additional,v.v_estimation AS estimation," +
                "vd.initial AS initial, vd.fee AS fee, vd.driver AS driver " +
                "FROM booking B " +
                "JOIN vehicle v ON B.vid = v.vehicleid " +
                "JOIN login L ON B.uid = L.id " +
                "JOIN vehicledetail vd ON v.details_id = vd.id " +
                "WHERE B.bid=?";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, dpid);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer id =resultSet.getInt("id");
                String email= resultSet.getString("email");
                Double price = resultSet.getDouble("price");
                Double additional = resultSet.getDouble("additional");
                String estimation = resultSet.getString("estimation");
                Double initial = resultSet.getDouble("initial");
                Double fee = resultSet.getDouble("fee");
                Double driver = resultSet.getDouble("driver");
                String pickaddress = resultSet.getString("pickaddress");
                String dropaddress = resultSet.getString("dropaddress");
                System.out.println("id is"+id);
                BookingList.add(new Payment(id,email,price,additional,estimation,initial,fee,driver, pickaddress, dropaddress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BookingList;
    }

    public static void updatepaymnet(Booking booking) {
        Integer id = booking.getId();
        String query = "UPDATE booking SET Destination=?  WHERE bid=?";

        try {

            Connection connection = DbConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1,booking.getTotal());
            statement.setInt(2, id);

            statement.executeUpdate();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Payment> getuserpaybooking(Integer payid) {
        List<Payment> BookingList = new ArrayList<>();
        String query = "SELECT L.email AS email, " +
                "B.bid AS id, B.pickaddress AS pickaddress, B.dropaddress AS dropaddress, B.Destination AS destination, " +
                "v.v_price AS price, v.v_addtionalprice AS additional, v.v_estimation AS estimation, " +
                "vd.initial AS initial, vd.fee AS fee, vd.driver AS driver, " +  // Added comma before p.price
                "p.price AS amount " +
                "FROM booking B " +
                "JOIN vehicle v ON B.vid = v.vehicleid " +
                "JOIN login L ON B.uid = L.id " +
                "JOIN vehicledetail vd ON v.details_id = vd.id " +
                "JOIN payment p ON B.bid = p.bid " +
                "WHERE B.bid = ?";


        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, payid);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer id =resultSet.getInt("id");
                String email= resultSet.getString("email");
                Double price = resultSet.getDouble("price");
                Double additional = resultSet.getDouble("additional");
                String estimation = resultSet.getString("estimation");
                Double initial = resultSet.getDouble("initial");
                Double fee = resultSet.getDouble("fee");
                Double driver = resultSet.getDouble("driver");
                String pickaddress = resultSet.getString("pickaddress");
                String dropaddress = resultSet.getString("dropaddress");
                String destination = resultSet.getString("destination");
                String amount = resultSet.getString("amount");
                System.out.println("id is"+id);
                BookingList.add(new Payment(id,email,price,additional,estimation,initial,fee,driver, pickaddress, dropaddress,destination,amount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BookingList;
    }

    public static void updatepaymnetdetail(Booking booking) {
        Integer id = booking.getId();
        String query = "UPDATE booking SET status=?  WHERE bid=?";

        try {

            Connection connection = DbConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1,"Finish");
            statement.setInt(2, id);

            statement.executeUpdate();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> getAllBooking() {
        List<Booking> BookingList = new ArrayList<>();
        LocalDate today = LocalDate.now();
        Date todayDate = Date.valueOf(today);

        String query = "SELECT L.Name AS name, v.v_name AS vehicle, " +
                "B.bid AS id ,B.pickaddress AS pickaddress, B.dropaddress AS dropaddress, B.picktime AS picktime  , B.pickdate AS pickdate " +
                "FROM booking B " + "JOIN vehicle v ON B.vid = v.vehicleid " + "JOIN login L ON B.uid = L.id " +
                "WHERE B.pickdate = ? AND B.status=?";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDate(1, todayDate);
            preparedStatement.setString(2, "Accept");

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
                "B.bid AS id , B.pickaddress AS pickaddress, B.dropaddress AS dropaddress, B.picktime AS picktime  ,B.pickdate AS pickdate, B.status AS status "+
                "FROM booking B " + "JOIN vehicle v ON B.vid = v.vehicleid " + "JOIN login L ON B.uid = L.id "+
                "WHERE B.status!=?";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             preparedStatement.setString(1,"Finish");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String vehicletype = resultSet.getString("vehicle");
                String time = resultSet.getString("picktime");
                String Date = resultSet.getString("pickdate");
                String pickaddress = resultSet.getString("pickaddress");
                String dropaddress = resultSet.getString("dropaddress");
                String status = resultSet.getString("status");

                BookingList.add(new AdminBook(id,name, vehicletype, time,Date, pickaddress, dropaddress,status));
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
                "vd.initial AS initial, vd.fee AS fee," +
                "B.bid AS id ,B.driver AS driver, B.pickaddress AS pickaddress, B.dropaddress AS dropaddress, B.picktime AS picktime, " +
                "B.pickdate AS pickdate " +
                "FROM booking B " +
                "JOIN vehicle v ON B.vid = v.vehicleid " +
                "JOIN vehicledetail vd ON v.details_id = vd.id " +
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
                    Double initial = resultSet.getDouble("initial");
                    Double fee = resultSet.getDouble("fee");
                    String time = resultSet.getString("picktime");
                    String date = resultSet.getString("pickdate");
                    String pickaddress = resultSet.getString("pickaddress");
                    String dropaddress = resultSet.getString("dropaddress");

                    BookSelect.add(new AdminBook(id,name, email, mobile, nic, driver,vehicletype, price,initial,fee, estimate,additional, time, date, pickaddress, dropaddress));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return BookSelect;

    }

    public List<Booking> getmybook(Integer userId) {
        List<Booking> BookList  = new ArrayList<>();
        LocalDate today = LocalDate.now();  // Get today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        String query1 = "SELECT  v.v_name AS vehicle, " +
                "B.bid AS bid ,B.pickaddress AS pickaddress, B.dropaddress AS dropaddress, B.picktime AS picktime  , B.pickdate AS pickdate " +
                "FROM booking B " + "JOIN vehicle v ON B.vid = v.vehicleid " + "JOIN login L ON B.uid = L.id " +
                "WHERE B.uid = ? AND B.pickdate >= ? AND B.status=?" +
                "ORDER BY B.pickdate ASC";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query1)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setDate(2, Date.valueOf(today));
            preparedStatement.setString(3,"Accept");


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String date = resultSet.getString("pickdate");
                LocalDate pickDate = LocalDate.parse(date, formatter);

                String bid =resultSet.getString("bid");
//                System.out.println("id ek  is"+bid);
                String vehicle = resultSet.getString("vehicle");
                String time = resultSet.getString("picktime");
                String pickaddress = resultSet.getString("pickaddress");
                String dropaddress = resultSet.getString("dropaddress");

                BookList.add(new Booking(bid, vehicle,time,pickDate, pickaddress, dropaddress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BookList;
    }
}

