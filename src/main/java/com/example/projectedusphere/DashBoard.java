package com.example.projectedusphere;

public class DashBoard implements DashboardInterface{
    protected static String userName;
    protected static String userEmail;
    protected static String userRoll;
    protected static String userRegistration;
    protected static String userPassword;
    protected static String userBio;



    public String getUserName(){
        userName = EDUSPHEREUtils.getName();
        return userName;
    }
    public String getEmail(){
        return userEmail = EDUSPHEREUtils.getEmail();
    }
    public String getRoll(){
        return userRoll = EDUSPHEREUtils.getRoll();
    }
    public String getRegistration(){
        return userRegistration = EDUSPHEREUtils.getRegistrationNumber();
    }
    public String getPassword(){
        userPassword = EDUSPHEREUtils.getPassword();
        return userPassword;
    }
    public String getUserBio(){
        return  userBio = EDUSPHEREUtils.getBio();
    }

}
