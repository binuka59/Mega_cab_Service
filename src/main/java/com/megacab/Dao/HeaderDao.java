package com.megacab.Dao;

import com.megacab.model.Header;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeaderDao {



    public static List<Header> getAllheader(int userId) {
        List<Header> headerList = new ArrayList<>();
        String query = "SELECT * FROM login WHERE id=?";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            String conid = String.valueOf(userId);

            preparedStatement.setString(1,conid);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("Name");


                headerList.add(new Header(name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return headerList;
    }

}
