package com.example.project1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Class to fetch the data from Database
public abstract class FetchFromDB {
    public static List<UserData> fetchUserData(){
        List<UserData> userList = new ArrayList<>();

        try{
            Connection connection = DBConnection.connection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM clients_data"); //Query statement to fetch all the data from database

            while(resultSet.next()){
                String firstName = resultSet.getString("first_name");
                String secondName = resultSet.getString("second_name");
                String passportNum = resultSet.getString("passport");
                String email = resultSet.getString("email");
                int reserved_room = resultSet.getInt("reserved_room");
                String reservation_period = resultSet.getString("reservation_period");

                UserData user = new UserData(firstName, secondName, passportNum, email, reserved_room, reservation_period);
                userList.add(user);
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
            }
        return userList;
    }

    //method to use the main function .fetchFromDB()
    public static void fetchedData(){
        List<UserData> userList = FetchFromDB.fetchUserData();
        for (UserData user : userList){
            System.out.println(user.displayUser());
        }
    }
}
