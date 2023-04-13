package com.example.projectedusphere;

public class Notes extends DashBoard{
    protected String note;
    protected String reg;
    public String getKeepnote(){return note = EDUSPHEREUtils.getKeepnote();}
    public String getRegistration(){
        return reg = super.getRegistration();
    }
}
