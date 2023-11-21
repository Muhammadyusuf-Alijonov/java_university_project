package com.example.project1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

// Class to delete the user from the Database
public class Delete {

    public static void deleteFromDB(String passport){
        List<UserData> userList = FetchFromDB.fetchUserData();
        for (UserData user : userList){
            if (user.getPassportNum().equals(passport)){
                String deleteQuery = "DELETE FROM clients_data WHERE passport = ?";
                try {
                    Connection connection = DBConnection.connection();
                    PreparedStatement stmt = connection.prepareStatement(deleteQuery);
                    stmt.setString(1,user.getPassportNum());
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void deleteUser(String passport){
        Delete.deleteFromDB(passport);
    }
}