package com.example.projectedusphere;

public class Subject extends DashBoard{

    protected static String[] Subject;
    protected String reg;

    public String[] getSubject(){
        return Subject = EDUSPHEREUtils.getSubject();
    }
    public String getRegistration(){
        return reg = super.getRegistration();
    }
}
