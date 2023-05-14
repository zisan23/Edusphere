package com.example.projectedusphere;

public class Attendance extends DashBoard implements AttendanceInterface{
    protected String reg;
    protected static String[] CT;
    protected static String[] CA;

    public String[] getClassTaken(){return CT = EDUSPHEREUtils.getClasstaken();}
    public String[] getClassAttended(){return CA = EDUSPHEREUtils.getClassattended();}

    public String getRegistration(){
        return reg = super.getRegistration();
    }
}
