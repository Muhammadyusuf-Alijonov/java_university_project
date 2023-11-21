package com.example.project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Class to connect to DB using JDBC
public class DBConnection {
    // Connection method
    // Connection to Database using JDBC driver
    public static Connection connection (){
        try{
            // Loading the Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Creating connection by pasting URL
            String url = "jdbc:mysql://localhost:3306/hotel_clients_list";
            String username = "root";
            String password = "";

            // Connecting to database with url, username and password using built-in method .getConnection
            return DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
