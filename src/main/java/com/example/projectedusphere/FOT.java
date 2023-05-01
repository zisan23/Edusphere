package com.example.projectedusphere;

public class FOT extends DashBoard{
    protected  String time;
    protected String time2;
    protected String reg;

    public String getTime(){
        return time = EDUSPHEREUtils.getTime();
    }
    public String getTime2(){
        return time2 = EDUSPHEREUtils.getTime2();
    }
    public String getRegistration(){
        return reg = super.getRegistration();
    }
}
