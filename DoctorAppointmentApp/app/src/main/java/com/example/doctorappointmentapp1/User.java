package com.example.doctorappointmentapp1;

public class User {
    String userID;
    String userName;
    String password;
    String MobileNum;
    String Email;

    public User(String userID, String userName, String password, String Email, String MobileNum) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.MobileNum = MobileNum;
        this.Email = Email;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getMobileNum() {
        return MobileNum;
    }

    public String getEmail() {
        return Email;
    }
}
