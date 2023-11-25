package com.example.project1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Class to add the data to Database
public abstract class AddToDB {
    public static boolean insertIntoDB(SingleUserData user){
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
            return rowAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void addToDb(String fname, String sname, String passport, String email, int reserved_room, String reservation_period){
        SingleUserData user = SingleUserData.getInstance();
        user.setAll(fname, sname, passport, email, reserved_room, reservation_period);
        AddToDB.insertIntoDB(user);
    }
}
