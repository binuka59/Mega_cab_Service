package com.megacab.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cab_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Binu@9876";

    private static DbConnection instance;
    private Connection connection;

    private DbConnection()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            System.out.println("Database connection established successfully!");

        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to establish database connection!");
        }
    }
    public static DbConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DbConnection();
        }
        return instance;
    }

    public Connection getConnection() {

        return connection;
    }
}
