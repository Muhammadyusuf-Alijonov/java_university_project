package com.example.project1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Class to fetch the data from Database
public abstract class FetchFromDB {
    public static ResultSet fetchUserData(){
        try{
            Connection connection = DBConnection.connection();
            Statement stmt = connection.createStatement();
            return stmt.executeQuery("SELECT * FROM clients_data"); //Query statement to fetch all the data from database
        } catch (SQLException e){
            throw new RuntimeException(e);
            }
    }

    //method to use the main function .fetchFromDB()
//    public static void fetchedData(){
//        List<SingleUserData> userList = FetchFromDB.fetchUserData();
//        for (SingleUserData user : userList){
//            System.out.println(user.displayUser());
//        }
//    }
}
