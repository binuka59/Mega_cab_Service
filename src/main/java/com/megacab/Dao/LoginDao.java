package com.megacab.Dao;

import com.megacab.model.AdminBook;
import com.megacab.model.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoginDao {

//    public static void addData(Login login)
//    {
//        String query = "INSERT INTO login (name,email,password,status) VALUES (?,?,?,?)";
//
//        try (Connection connection = DbConnectionFactory.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, login.getName());
//            statement.setString(2, login.getEmail());
//            statement.setString(3, login.getPassword());
//            statement.setString(4, login.getStatus());
//
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }

    public static List<Login> getAllLogin() {
        List<Login> Login = new ArrayList<>();
        String query = "SELECT * FROM login";

        try {
            Connection Connection = DbConnectionFactory.getConnection();
            Statement statement1 = Connection.createStatement();
            ResultSet resultSet = statement1.executeQuery(query);

            while (resultSet.next()) {

                String password = resultSet.getString("password");
                String email = resultSet.getString("email");

                Login.add(new Login(email,password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Login;
    }

    public static void updateData(Login login) {
        Integer userid = login.getId();
        String query = "UPDATE login SET Name=? ,email=?, mobile=?, nic=? WHERE id=?";

        try {

            Connection connection = DbConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, login.getName());
            statement.setString(2, login.getEmail());
            statement.setString(3, String.valueOf(login.getMobile()));
            statement.setString(4, login.getNic());
            statement.setInt(5, userid);

            statement.executeUpdate();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addaccount(Login login) {
        String query = "INSERT INTO login (Name,email,password,mobile,nic,status) VALUES (?,?,?,?,?,?)";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login.getName());
            statement.setString(2, login.getEmail());
            statement.setString(3, login.getPassword());
            statement.setString(4, String.valueOf(login.getMobile()));
            statement.setString(5, login.getNic());
            statement.setString(6, login.getStatus());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateAdmin(Login login) {
        String query = "UPDATE login SET Name=? ,email=? , mobile=?, nic=? WHERE id=?";

        try {

            Connection connection = DbConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, login.getName());
            System.out.println("nameis"+login.getName());
            statement.setString(2, login.getEmail());
            statement.setString(3, login.getMobile());
            statement.setString(4, login.getNic());
            statement.setInt(5, login.getId());

            statement.executeUpdate();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public List<Login> getAdminDetails() {
        List<Login> AdminList = new ArrayList<>();
        String query = "SELECT * FROM login WHERE status=?";

        try {
            Connection Connection = DbConnectionFactory.getConnection();
            PreparedStatement  statement1 = Connection.prepareStatement(query);
            statement1.setString(1,"Admin");
            ResultSet resultSet = statement1.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("Name");
                String email = resultSet.getString("email");
                String mobile = resultSet.getString("mobile");
                String nic = resultSet.getString("nic");

                AdminList.add(new Login(id,name,email,mobile,nic));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AdminList;

    }

    public List<Login> getupdateadmin(String id) {
        List<Login> AdminList = new ArrayList<>();
        String query ="SELECT * FROM login WHERE id=?";
        try {
            Connection connection = DbConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,id);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                String name = rs.getString("Name");
                String email = rs.getString("email");
                String mobile = rs.getString("mobile");
                String nic = rs.getString("nic");

                AdminList.add(new Login(id,name,email,mobile,nic));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  AdminList;
    }
}

