package com.example.project1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Class to delete the user from the Database
public abstract class Delete {

    public static void deleteFromDB(String passport){
        ResultSet resultSet = FetchFromDB.fetchUserData();
        SingleUserData user = SingleUserData.getInstance();
        while(true){
            try {
                if (!resultSet.next()) break;

                user.setAll(resultSet.getString("first_name"),resultSet.getString("second_name"),resultSet.getString("passport"),resultSet.getString("email"),resultSet.getInt("reserved_room"),resultSet.getString("reservation_period"));

                if (user.getPassportNum().equals(passport)){
                    String deleteQuery = "DELETE FROM clients_data WHERE passport = ?";
                    Connection connection = DBConnection.connection();
                    PreparedStatement stmt = connection.prepareStatement(deleteQuery);
                    stmt.setString(1,user.getPassportNum());
                    stmt.executeUpdate();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // This method used to use the method above in GUI
    public static void deleteUser(String passport){
        Delete.deleteFromDB(passport);
    }
}