package Controllers;

import com.example.projectedusphere.DashBoard;
import com.example.projectedusphere.EDUSPHEREUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class DashboardController extends DashBoard implements Initializable {

    @FXML
    private Button profile;
    @FXML
    private Button books;
    @FXML
    private Button time;

    @FXML
    private Button attendence;
    @FXML
    private Button result;
    @FXML
    private Label userName;
    @FXML
    private Label userEmail;
    @FXML
    private Label userRoll;
    @FXML
    private Label userRegistration;
    @FXML
    private TextField profileBioinput;
    @FXML
    private Label profileBio;
    @FXML
    private Button saveButton;
    @FXML
    private Label userPassword;

    private  String name;
    private  String email;
    private String roll;
    private String reg;
    private String pass;

    private String BIO;

    public DashboardController(){
        super();
        setUserName(super.getUserName());
        setEmail(super.getEmail());
        setRoll(super.getRoll());
        setReg(super.getRegistration());
        setPass(super.getPassword());
        setBio(super.getUserBio());
    }

    public void setBio(String text){
        this.BIO = text;
    }
    public String getUserBio(){
        return this.BIO;
    }

    public void setUserName(String Name) {
        this.name = Name;
    }

    public String getUserName(){
        return this.name;
    }
    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getEmail(){
        return this.email;
    }
    public void setRoll(String Roll) {
        this.roll = Roll;
    }
    public String getRoll(){
        return this.roll;
    }
    public void setReg(String Reg) {
        this.reg = Reg;
    }
    public String getRegistration(){
        return this.reg;
    }
    public void setPass(String Pass) {
        this.pass = Pass;
    }
    public String getPassword(){
        return this.pass;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //NavigationHandler

        userName.setText(name);
        userEmail.setText(email);
        userRoll.setText(roll);
        userRegistration.setText(reg);
        userPassword.setText(pass);
        profileBio.setWrapText(true);
        profileBio.setText(BIO);
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BIO = profileBioinput.getText();
                EDUSPHEREUtils.bio(event, BIO, reg);
                profileBio.setWrapText(true);
                profileBio.setText(BIO);
            }

        });



    }
}
