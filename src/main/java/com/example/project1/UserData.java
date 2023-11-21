package com.example.project1;

class UserData {
    private String firstName;
    private String secondName;
    private String passportNum;
    private String email;
    private int reserved_room;
    private String reservation_period;

    public UserData(String fName, String sName, String passportNum, String email, int room, String period) {
        this.firstName = fName;
        this.secondName = sName;
        this.passportNum = passportNum;
        this.email = email;
        this.reserved_room = room;
        this.reservation_period = period;
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
}