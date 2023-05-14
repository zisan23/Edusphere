package Controllers;

import com.example.projectedusphere.DashBoard;
import com.example.projectedusphere.EDUSPHEREUtils;
import com.example.projectedusphere.FeatureSelector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
    @FXML
    private ImageView exit;
    @FXML
    private Button note;

    @FXML
    private Button sub;
    @FXML
    private Button logout;

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
        FeatureSelector.SelectScene(profile, books, time, attendence, result, exit, note);

        userName.setText(name);
        userEmail.setText(email);
        userRoll.setText(roll);
        userRegistration.setText(reg);
        userPassword.setText(pass);


        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BIO = profileBioinput.getText();
                EDUSPHEREUtils.bio(event, BIO, reg);
                profileBio.setWrapText(true);
                profileBio.setText(BIO);
            }

        });

        profileBio.setWrapText(true);
        profileBio.setText(BIO);

        sub.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EDUSPHEREUtils.changeScene(event,"Subjects.fxml", "Subject", null, null);
            }
        });

        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EDUSPHEREUtils.changeScene(event, "LogIn.fxml", "Log In", null, null);
            }
        });

    }
}
