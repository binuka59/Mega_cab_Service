package com.megacab.Dao;

import com.megacab.model.Login;
import com.megacab.model.Register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {
    public static void addData(Register register) {
        String query = "INSERT INTO login (name,password,email,status) VALUES (?, ?,?,?)";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,register.getName());
            statement.setString(2, register.getPassword());
            statement.setString(3, register.getEmail());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Use proper logging in real applications
        }
    }
}
