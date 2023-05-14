package Controllers;

import com.example.projectedusphere.DashBoard;
import com.example.projectedusphere.EDUSPHEREUtils;
import com.example.projectedusphere.Subject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class SubjectController extends com.example.projectedusphere.Subject {

    @FXML
    private Button submit;

    @FXML
    private TextField sub1;

    @FXML
    private TextField sub2;

    @FXML
    private TextField sub3;

    @FXML
    private TextField sub4;

    @FXML
    private TextField sub5;

    private String reg;

    private String[] subject = new String[5];


    public void setReg(String Reg) {
        this.reg = Reg;
    }
    public String getRegistration(){
        return this.reg;
    }

    public String[] getSubject(){
        return subject;
    }
    public void setSubject(String[] Sub){
        subject = Sub;
    }

    public SubjectController(){
        super();
        setSubject(super.getSubject());
        setReg(super.getRegistration());
    }

    public void Return(ActionEvent event){
        EDUSPHEREUtils.changeScene(event, "Dashboard.fxml", "DashBoard", null, null);
    }

    public void SubmitSubject(ActionEvent event){
        subject[0] = sub1.getText();
        subject[1] = sub2.getText();
        subject[2] = sub3.getText();
        subject[3] = sub4.getText();
        subject[4] = sub5.getText();


        EDUSPHEREUtils.Subjects(event, subject, reg);

        EDUSPHEREUtils.changeScene(event, "Dashboard.fxml", "Profile", null, null);
    }



}
