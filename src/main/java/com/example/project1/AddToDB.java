package com.example.project1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Class to add the data to Database
public class AddToDB {
    public static void insertIntoDB(UserData user){
        try {
            Connection connection = DBConnection.connection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO clients_data (first_name, second_name, passport, email, reserved_room, reservation_period) VALUES(?,?,?,?,?,?)");
            stmt.setString(1,user.getFirstName());
            stmt.setString(2,user.getSecondName());
            stmt.setString(3,user.getPassportNum());
            stmt.setString(4,user.getEmail());
            stmt.setInt(5,user.getReservedRoom());
            stmt.setString(6,user.getReservationPeriod());

            int rowAffected = stmt.executeUpdate();

            if (rowAffected > 0){
                System.out.println("User added to Database successfully!");
            }
        } catch (SQLException e) {
            System.out.println("User not added to Database, error occurred!");
            throw new RuntimeException(e);
        }
    }
    public static void addToDb(String fname, String sname, String passport, String email, int reserved_room, String reservation_period){
        UserData user = new UserData(fname, sname, passport, email, reserved_room, reservation_period);
        AddToDB.insertIntoDB(user);
        System.out.println("Updated Database:");
        FetchFromDB.fetchedData();
    }
}
