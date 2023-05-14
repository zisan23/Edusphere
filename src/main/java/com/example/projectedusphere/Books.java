package com.example.projectedusphere;

public class Books extends DashBoard{
    protected String reg;
    protected static String[] paths;

    public String getRegistration(){
        return reg = super.getRegistration();
    }

    public String[] getPaths(){
        return paths = EDUSPHEREUtils.getPaths();
    }

}
