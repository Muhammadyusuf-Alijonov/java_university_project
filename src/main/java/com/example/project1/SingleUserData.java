package com.example.project1;

public class SingleUserData {

    private static SingleUserData instance = new SingleUserData(); //Single instance of the object SingleUserData
    private String firstName;
    private String secondName;
    private String passportNum;
    private String email;
    private int reserved_room;
    private String reservation_period;

    private SingleUserData() {
        this.firstName = "";
        this.secondName = "";
        this.passportNum = "";
        this.email = "";
        this.reserved_room = 0;
        this.reservation_period = "";
    }


    // Default getters
    public String displayUser(){
        return "First Name: " + firstName + "; Second Name: " + secondName + "; Passport Number: " + passportNum +
                "; Email: " + email + "; Reserved Room: " + reserved_room + "; Reservation Period: " + reservation_period;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public String getEmail() {
        return email;
    }

    public int getReservedRoom(){ return reserved_room; }
    public String getReservationPeriod(){ return  reservation_period;}

    public static SingleUserData getInstance(){ return instance; }//allows use of instance in the program

    //Used when fetching from or adding to the database
    public void setAll(String firstName, String secondName, String passportNum, String email, int reserved_room, String reservation_period){
        this.firstName = firstName;
        this.secondName = secondName;
        this.passportNum = passportNum;
        this.email = email;
        this.reserved_room = reserved_room;
        this.reservation_period = reservation_period;
    }
}