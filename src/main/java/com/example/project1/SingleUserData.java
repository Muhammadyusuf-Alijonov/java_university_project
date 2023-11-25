package com.example.project1;

public class SingleUserData {

    private static SingleUserData instance = new SingleUserData();
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

    public static SingleUserData getInstance(){ return instance; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) { this.secondName = secondName;}

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setReservedRoom(int reserved_room){ this.reserved_room = reserved_room; }
    public void setReservationPeriod(String reservation_period){ this.reservation_period = reservation_period;}

    public void setAll(String firstName, String secondName, String passportNum, String email, int reserved_room, String reservation_period){
        this.firstName = firstName;
        this.secondName = secondName;
        this.passportNum = passportNum;
        this.email = email;
        this.reserved_room = reserved_room;
        this.reservation_period = reservation_period;
    }
}